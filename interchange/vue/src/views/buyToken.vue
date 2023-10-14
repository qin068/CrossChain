<style>
    .currentSellOrder{
        margin-top: 50px;
    }
    .title{
        font-size: 24px;
        font-weight: bolder;
    }
    .CreateOrder{
        background-color: white;
        box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04);
        padding-top: 2%;
        padding-left: 2%;
        padding-right: 2%;
        padding-bottom: 0.2%;
    }
    .demo-form-inline{
        padding-left: 2%;
    }
    </style>
    <template>
        <div class="common-layout">
        <el-container >
            <el-header>
                <topMenu></topMenu>
            </el-header>
            <el-container>
                <el-aside width="200px" style="margin-left:20px; margin-top: 40px;">
                    <sideMenu></sideMenu>
                </el-aside>
                <el-main height="800px">
                    <!-- 当前售卖订单和购买订单 -->
                    <div class="currentSellOrder">
                        <div style="font-size:24px;font-weight:bolder;margin-left:18%;display:inline;">正在售卖的代币</div>
                        <el-link icon="el-icon-refresh-right" @click="RefreshForSell" type="primary" style="margin-left:10px">刷新</el-link>
                        <div style="font-size:24px;font-weight:bolder;margin-left:40%;display:inline;">已申请购买的代币</div>
                        <el-link icon="el-icon-refresh-right" @click="RefreshForBuy" type="primary" style="margin-left:10px">刷新</el-link>
                        <br><br>
                        <!-- 当前售卖的代币 -->
                        <el-row :gutter="20">
                        <el-col :span="12">
                            <el-table
                            :data="sellData"
                            style="width: 100%;"
                            max-height="250"
                            :empty-text=this.emptySell>
                            <el-table-column
                                label="订单编号"
                                width="180">
                                <template v-slot="scope">
                                <span style="margin-left: 10px">{{ scope.row.orderId }}</span>
                                </template>
                            </el-table-column>
                            <el-table-column
                                label="售卖的代币名称"
                                width="180">
                                <template v-slot="scope">
                                <span style="margin-left: 10px">{{ scope.row.sourceToken }}</span>
                                </template>
                            </el-table-column>
                            <el-table-column
                                label="售卖的代币数量"
                                width="180">
                                <template v-slot="scope">
                                <span style="margin-left: 10px">{{ scope.row.sourceNum }}</span>
                                </template>
                            </el-table-column>
                            <el-table-column
                                label="目标代币名称"
                                width="180">
                                <template v-slot="scope">
                                <span style="margin-left: 10px">{{ scope.row.targetToken }}</span>
                                </template>
                            </el-table-column>
                            <el-table-column
                                label="每个售卖代币的兑换价格"
                                width="180">
                                <template v-slot="scope">
                                <span style="margin-left: 10px">{{ scope.row.price }}</span>
                                </template>
                            </el-table-column>
                            </el-table>
                        </el-col>
                        <!-- 当前已申请购买的代币 -->           
                        <el-col :span="12">
                            <el-table
                            :data="buyData"
                            style="width: 100%;"
                            max-height="250"
                            :empty-text=this.emptyBuy>
                            <el-table-column
                                label="订单编号"
                                width="100"
                                >
                                <template v-slot="scope">
                                <span style="margin-left: 10px">{{ scope.row.orderId }}</span>
                                </template>
                            </el-table-column>
                            <el-table-column
                                label="申请购买的代币名称"
                                width="180">
                                <template v-slot="scope">
                                <span style="margin-left: 10px">{{ scope.row.sourceToken }}</span>
                                </template>
                            </el-table-column>
                            <el-table-column
                                label="申请购买数量"
                                width="180">
                                <template v-slot="scope">
                                <span style="margin-left: 10px">{{ scope.row.sourceNum }}</span>
                                </template>
                            </el-table-column>
                            <el-table-column
                                label="当前代币名称"
                                width="180">
                                <template v-slot="scope">
                                <span style="margin-left: 10px">{{ scope.row.targetToken }}</span>
                                </template>
                            </el-table-column>
                            <el-table-column
                                label="每代币申请兑换价格"
                                width="180">
                                <template v-slot="scope">
                                <span style="margin-left: 10px">{{ scope.row.price }}</span>
                                </template>
                            </el-table-column>
                            <el-table-column fixed="right" label="操作">
                                <template v-slot="scope">
                                <el-button
                                    size="mini"
                                    type="danger"
                                    @click="handleDelete(scope.$index, scope.row)">删除</el-button>
                                </template>
                            </el-table-column>
                            </el-table>
                        </el-col>
                        </el-row>  
                    </div>  
                    <br><br>

                    <!-- 第二行，创建订单簿、售卖订单、余额查询 -->
                    <el-row :gutter="20">
                        <!--不再提供创建订单簿功能，创建者创建后自身可卖，对方可买，即必须有人卖才可买，没法创建购买的通道 -->
                        <!-- 创建购买订单 -->
                        <el-col :span="12">
                            <div class="title" style="text-align: center">申请购买代币</div><br>
                            <div class="CreateOrder">
                                <el-form :model="createBuyOrder">
                                    <el-form :inline="true" :model="firstLine">
                                        <el-form-item label="当前账户地址">
                                            <el-input v-model="firstLine.currentAddress" placeholder="当前账户地址"/>
                                        </el-form-item>
                                        <el-form-item label="当前代币名称">
                                            <el-input v-model="firstLine.sourceToken" placeholder="当前代币名称" disabled/>
                                        </el-form-item>
                                    </el-form>
                                <el-form-item label="目标区块链" style="width:100%">
                                    <el-select v-model="createBuyOrder.targetChain" placeholder="请选择想要购买代币的区块链" style="float:left">
                                        <el-option v-for="item in options" :key="item.targetChainName" :value="item.targetChannel" :label="item.targetChainName" @click="labelClick(item.targetChainName)"></el-option>
                                    </el-select>
                                    <el-button style="margin-left: 1%" @click="getTargetChain">查询可供选择的目标区块链</el-button>
                                </el-form-item>
                                <el-form-item :label=this.$data.createBuyOrder.targetToken>
                                    <el-input v-model="createBuyOrder.targetNum" placeholder="购买数量"></el-input>
                                </el-form-item>
                                <el-form-item :label=this.$data.firstLine.sourceToken>
                                    <el-input v-model="createBuyOrder.sourceNum" placeholder="购买价格(1购买代币=几当前代币)"></el-input>
                                </el-form-item>
                                <el-form-item>
                                    <el-button type="primary" @click="createBuy" style="display:block;margin:0 auto">申请购买代币</el-button>
                                </el-form-item>
                                </el-form>
                            </div>
                        </el-col>
                        <!-- 查询余额 -->
                        <el-col :span="12">
                            <div class="title" style="display:inline; padding-left: 38%;">查询账户余额</div>
                            <el-button type="primary" icon="el-icon-search" style="display:inline;margin-left:20px" @click="QueryBalance">查询</el-button>
                            <br><br>
                            <el-table
                            :data="balanceData"
                            style="width: 100%">
                            <el-table-column
                                label="代币名称"
                                width="250">
                                <template v-slot="scope">
                                <span style="margin-left: 10px">{{ scope.row.tokenName }}</span>
                                </template>
                            </el-table-column>
                            <el-table-column
                                label="代币数量">
                                <template v-slot="scope">
                                <span style="margin-left: 10px">{{ scope.row.tokenNum }}</span>
                                </template>
                            </el-table-column>
                            </el-table>
                        </el-col>
                    </el-row>

                </el-main>
            </el-container>
        </el-container>
        </div>
        
    </template>
    
    <script>
    import { computed } from "vue";
    import topMenu from '../components/top-menu.vue';
    import sideMenu from '../components/side-menu.vue';
    export default {
        name: 'sellToken',
        components: {
            topMenu,
            sideMenu
        },
        data() {
            return {
                sellData: [],//售卖列表
                buyData: [],//购买列表
                balanceData: [],//余额列表
                targetChainId: [],//目标链的两个Id值
                options:[],//存目标链名称和channel号,
                label:'',//存储目标链选择框中最终选择的链id,
                createBuyOrder: {
                    targetChain:'',
                    targetToken: '',
                    sourceNum: '',
                    targetNum: ''
                },
                emptySell:'No Data',
                emptyBuy:'No Data',
                firstLine: {
                    currentAddress: '',
                    sourceToken: '',
                }
            }
        },
        methods: {
            //去除字符串前后的空格（cosmos返回的字符串前总会多一个空格）
        Trim(str){ 
            return str.replace(/(^\s*)|(\s*$)/g, ""); 
        },
        //获取当前节点的链名
		getChainName(){
			return this.$axios({
				method:'get',
				url:'/api/selectAllAccount'
			}).then((response) =>{     
				// console.log(response);
				if(response.data.code==200){
                    let responseData = response.data.data; 
					for(let i=0; i<responseData.length; i++){
						if(this.$store.getters['common/wallet/address']==responseData[i].address){
							return responseData[i].chain;
						}
					}
				}
			}).catch((error) =>{
				console.log(error);
			})
		},
        //根据链名转化为链ID
        getChainByName(chainName){
            if(chainName=="CompanyA"){
				return [1,"CompanyA","Acx"];
			}else if(chainName=="CompanyB"){
				return [2,"CompanyB","Bcx"];
			}else if(chainName=="CompanyC"){
				return [3,"CompanyC","Ccx"];
			}
        },
        //根据select的Company名转化为token
        getTokenByCompany(companyName){
            if(companyName=="CompanyA"){
				return "Acx";
			}else if(companyName=="CompanyB"){
				return "Bcx";
			}else if(companyName=="CompanyC"){
				return "Ccx";
			}
        },
        // 获取通信目标链,先筛选出port是ibcdex的channel，然后看from是否是本链，是则放入to和channelId
		getTargetChain(){
			//初始化下拉选项
			this.options = [];
			this.getChainName().then(data=>{
                this.$data.firstLine.sourceToken = this.getChainByName(data)[2];
				this.$axios({
					method:'get',
					url:'/api/selectAllChannel'
				}).then((response) =>{     
					// console.log(response);
					if(response.data.code==200){
						let responseData = response.data.data; 
						for(let i=0; i<responseData.length; i++){
							//如果连接功能是ibcdex
							if(responseData[i].port == "ibcdex"){
								//如果发送链是本链
								if(responseData[i].from == data){
									//获取接收链
									let chainToCompany = this.getChainByName(responseData[i].to)[1];
									//将接收链名称和Channel号传入下拉选项
									this.options.push({
										targetChainName: chainToCompany,
										targetChannel: responseData[i].channelId
									});
								}else{
									continue;
								}
							}else{
								continue;
							}
						}
					}
				}).catch((error) =>{
					alert(error);
				})
			});
		},
        //获取el-select中的label内容
		labelClick(data){
			this.label=data;
            this.$data.createBuyOrder.targetToken = this.getTokenByCompany(data);
		},
        //创建购买代币订单
        createBuy() {
            this.getChainName().then(data=>{
                let chain = this.getChainByName(data)[0];
                let chainString = chain.toString();
                this.$axios({
                    method:'get',
                    url:'/api/sendBuyOrder',
                    params:({    //这里是发送给后台的数据
                        sourceToken:this.$data.createBuyOrder.targetToken,
                        sourceNum:this.$data.createBuyOrder.targetNum,
                        targetToken:this.$data.firstLine.sourceToken,
                        price:this.$data.createBuyOrder.sourceNum,
                        chain:chainString,
                        channel:this.createBuyOrder.targetChain
                    })
                }).then((response) =>{  
                    console.log(response);
                    if(response.data.code==200){
                        this.$confirm('申请购买代币成功',{
                        confirmButtonText: '确认',
                        type: 'success'
                        });
                    }else{
                        this.$confirm('申请购买代币失败：'+response.data.data,{
                        confirmButtonText: '确认',
                        type: 'error'
                        });
                    }    
                }).catch((error) =>{
                    this.$confirm('申请购买代币失败：'+error,{
                        confirmButtonText: '确认',
                        type: 'error'
                    });  
                })
            });
        },
        getChainFromCoin(coin){
            if(coin == "Acx"){
                return "CompanyA";
            }else if(coin == "Bcx"){
                return "CompanyB";
            }else if(coin == "Ccx"){
                return "CompanyC";
            }
        },
        handleDelete(index, row) {
            this.getChainName().then(data=>{
                // console.log(data);
                let chain = this.getChainByName(data)[0];
                let chainString = chain.toString();
                let targetChainName = this.getChainFromCoin(this.Trim(row.sourceToken));
                console.log("row.sourceToken",row.sourceToken);
                console.log("targetChainName",targetChainName);
                let channel;
                this.$axios({
					method:'get',
					url:'/api/selectAllChannel'
				}).then((response) =>{     
					// console.log("channel",response);
					if(response.data.code==200){
						let responseData = response.data.data; 
						for(let i=0; i<responseData.length; i++){
							//如果连接功能是ibcdex
							if(responseData[i].port == "ibcdex"){
								//如果发送链是本链
								if(responseData[i].from == data){
									if(responseData[i].to == targetChainName){
                                        channel = responseData[i].channelId;
                                        break;
                                    }
								}else{
									continue;
								}
							}else{
								continue;
							}
						}
                        this.$axios({
                            method:'get',
                            url:'/api/cancelBuyOrder',
                            params:({    //这里是发送给后台的数据
                                sourceToken:this.Trim(row.sourceToken),
                                targetToken:row.targetToken,
                                orderId:row.orderId,
                                chain:chainString,
                                channel:channel
                            })
                        }).then((response) =>{  
                            console.log("cancelBuy",response);
                            if(response.data.code==200){
                                this.$confirm('取消订单成功',{
                                confirmButtonText: '确认',
                                type: 'success'
                                });
                                this.buyData.splice(index,1);
                            }else{
                                this.$confirm('取消订单失败：'+response.data.data,{
                                confirmButtonText: '确认',
                                type: 'error'
                                });
                            }    
                        }).catch((error) =>{
                            this.$confirm('取消订单失败：'+error,{
                                confirmButtonText: '确认',
                                type: 'error'
                            });  
                        })
					}
				}).catch((error) =>{
					alert("获取当前订单的通道号失败："+error);
				})
            }); 
        },
        RefreshForSell(){
            this.sellData = undefined;
            this.sellData = new Array();
            this.getChainName().then(data=>{
                let chain = this.getChainByName(data)[0];
                let targetChainId1, targetChainId2;
                if(chain==1){
                    targetChainId1=2;
                    targetChainId2=3;
                }else if(chain==2){
                    targetChainId1=1;
                    targetChainId2=3;
                }else if(chain==3){
                    targetChainId1=1;
                    targetChainId2=2;
                }
                let targetChainString1 = targetChainId1.toString();
                let targetChainString2 = targetChainId2.toString();
                this.$axios({
                    method:'get',
                    url:'/api/listSellOrder',
                    params:({    
                        chain:targetChainString1
                    })
                }).then((response) =>{  
                    // console.log("listSell",response);   
                    let responseData = response.data.data; 
                    if(response.data.code==200){
                        for (let i = 0; i < responseData.length; i++) {
                            if(data == this.getChainFromCoin(responseData[i].targetToken)){
                                this.sellData.push({
                                    orderId:responseData[i].orderId,
                                    sourceToken:responseData[i].sourceToken,
                                    sourceNum:responseData[i].sourceNum,
                                    targetToken:responseData[i].targetToken,
                                    price:responseData[i].price
                                })
                            }
                        }
                    }else{
                        this.$confirm('查询当前售卖的代币失败：'+response.data.msg,{
                        confirmButtonText: '确认',
                        type: 'error'
                        });
                    }  
                }).catch((error) =>{
                    this.$confirm('查询当前售卖的代币失败：'+error,{
                        confirmButtonText: '确认',
                        type: 'error'
                    }); 
                })  
                this.$axios({
                    method:'get',
                    url:'/api/listSellOrder',
                    params:({    
                        chain:targetChainString2
                    })
                }).then((response) =>{  
                    // console.log("listSell",response);   
                    let responseData = response.data.data; 
                    if(response.data.code==200){
                        for (let i = 0; i < responseData.length; i++) {
                            if(data == this.getChainFromCoin(responseData[i].targetToken)){
                                this.sellData.push({
                                    orderId:responseData[i].orderId,
                                    sourceToken:responseData[i].sourceToken,
                                    sourceNum:responseData[i].sourceNum,
                                    targetToken:responseData[i].targetToken,
                                    price:responseData[i].price
                                })
                            }
                        }
                    }else{
                        this.$confirm('查询当前售卖的代币失败：'+response.data.msg,{
                        confirmButtonText: '确认',
                        type: 'error'
                        });
                    }  
                }).catch((error) =>{
                    this.$confirm('查询当前售卖的代币失败：'+error,{
                        confirmButtonText: '确认',
                        type: 'error'
                    }); 
                })  
            });
        },
        //请求购买代币的订单
        RefreshForBuy(){
            this.buyData = undefined;
            this.buyData = new Array();
            this.getChainName().then(data=>{
                let chain = this.getChainByName(data)[0];
                let chainString = chain.toString();
                this.$axios({
                    method:'get',
                    url:'/api/listBuyOrder',
                    params:({    
                        chain:chainString
                    })
                }).then((response) =>{  
                    // console.log("listBuy",response);   
                    let responseData = response.data.data; 
                    if(response.data.code==200){
                        if(responseData.length == 0){
                            this.$data.emptyBuy = "当前没有购买订单"
                        }
                        for (let i = 0; i < responseData.length; i++) {
                            this.buyData.push({
                                orderId:responseData[i].orderId,
                                sourceToken:responseData[i].sourceToken,
                                sourceNum:responseData[i].sourceNum,
                                targetToken:responseData[i].targetToken,
                                price:responseData[i].price
                            })
                        }
                    }else{
                        this.$confirm('查询当前申请购买的代币失败：'+response.data.msg,{
                        confirmButtonText: '确认',
                        type: 'error'
                        });
                    }  
                }).catch((error) =>{
                    this.$confirm('查询当前申请购买的代币失败：'+error,{
                        confirmButtonText: '确认',
                        type: 'error'
                    }); 
                });
            });
        },
        //查询当前账户余额
        QueryBalance(){
            this.balanceData = undefined;
            this.balanceData = new Array();
            this.getChainName().then(data=>{
                // console.log(data);
                let chain = this.getChainByName(data)[0];
                let chainString = chain.toString();
                this.$axios({
                    method:'get',
                    url:'/api/bankBalances',
                    params:({    
                        address:this.$store.getters['common/wallet/address'],
                        chain:chainString
                    })
                }).then((response) =>{  
                    // console.log("queryBalance",response);
                    if(response.data.code==200){
                        let responseData = response.data.data;
                        for (let i = 0; i < responseData.length; i++) {
                            this.balanceData.push({
                                tokenName:responseData[i].tokenName,
                                tokenNum:responseData[i].tokenNum
                            })
                        }
                    }else{
                        this.$confirm('查询账户余额失败：'+response.data.msg,{
                        confirmButtonText: '确认',
                        type: 'error'
                        });
                    }    
                }).catch((error) =>{
                    this.$confirm('查询账户余额失败：'+error,{
                        confirmButtonText: '确认',
                        type: 'error'
                    });  
                })
            });
        },
        initialize(){
            //初始化，获取当前请求购买代币订单、目标区块链名称
            this.getTargetChain();
            this.RefreshForSell();
            this.RefreshForBuy();
            this.QueryBalance();
        },
        },
        mounted(){
            //初始化，获取当前的账户地址、目标区块链名称等
            this.firstLine.currentAddress = this.$store.getters['common/wallet/address'];
            this.initialize();
        }
        

    }
    </script>
    