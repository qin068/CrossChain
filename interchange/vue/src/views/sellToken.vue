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
                        <div style="font-size:24px;font-weight:bolder;margin-left:18%;display:inline;">当前请求购买本链代币的订单</div>
                        <el-link icon="el-icon-refresh-right" @click="RefreshForBuy" type="primary" style="margin-left:10px">刷新</el-link>
                        <div style="font-size:24px;font-weight:bolder;margin-left:31%;display:inline;">当前正在售卖代币订单</div>
                        <el-link icon="el-icon-refresh-right" @click="RefreshForSell" type="primary" style="margin-left:10px">刷新</el-link>
                        <br><br>
                        <!-- 当前请求购买代币的订单 -->
                        <el-row :gutter="20">
                        <el-col :span="12">
                            <el-table
                            :data="buyData"
                            style="width: 100%;"
                            max-height="250"
                            :empty-text=this.emptyBuy>
                            <el-table-column
                                label="订单编号"
                                width="100">
                                <template v-slot="scope">
                                <span style="margin-left: 10px">{{ scope.row.orderId }}</span>
                                </template>
                            </el-table-column>
                            <el-table-column
                                label="当前代币名称"
                                width="180">
                                <template v-slot="scope">
                                <span style="margin-left: 10px">{{ scope.row.sourceToken }}</span>
                                </template>
                            </el-table-column>
                            <el-table-column
                                label="请求购买本链代币数量"
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
                                label="每本链代币价格"
                                width="180">
                                <template v-slot="scope">
                                <span style="margin-left: 10px">{{ scope.row.price }}</span>
                                </template>
                            </el-table-column>
                            </el-table>
                        </el-col>
                        <!-- 当前售卖代币订单 -->           
                        <el-col :span="12">
                            <el-table
                            :data="sellData"
                            style="width: 100%;"
                            max-height="250"
                            :empty-text=this.emptySell>
                            <el-table-column
                                label="订单编号"
                                width="100"
                                >
                                <template v-slot="scope">
                                <span style="margin-left: 10px">{{ scope.row.orderId }}</span>
                                </template>
                            </el-table-column>
                            <el-table-column
                                label="售卖代币名称"
                                width="180">
                                <template v-slot="scope">
                                <span style="margin-left: 10px">{{ scope.row.sourceToken }}</span>
                                </template>
                            </el-table-column>
                            <el-table-column
                                label="售卖数量"
                                width="180">
                                <template v-slot="scope">
                                <span style="margin-left: 10px">{{ scope.row.sourceNum }}</span>
                                </template>
                            </el-table-column>
                            <el-table-column
                                label="请求交易目标代币名称"
                                width="180">
                                <template v-slot="scope">
                                <span style="margin-left: 10px">{{ scope.row.targetToken }}</span>
                                </template>
                            </el-table-column>
                            <el-table-column
                                label="每目标代币价格"
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
                        <!-- 创建订单簿 -->
                        <el-col :span="8">
                            <div class="title" style="text-align: center">初始化售卖通道</div><br>
                            <div class="CreateOrder">
                            <el-form ref="form" :model="createOrderPair">
                                <el-form-item label="当前账户地址">
                                    <el-input v-model="createOrderPair.currentAddress" placeholder="当前账户地址"></el-input>
                                </el-form-item>
                                <el-form-item label="目标区块链" style="width:100%">
                                    <el-select v-model="createOrderPair.targetChain" placeholder="请选择想要交换代币的目标区块链" style="float:left">
                                        <el-option v-for="item in options" :key="item.targetChainName" :value="item.targetChannel" :label="item.targetChainName" @click="labelClick(item.targetChainName)"></el-option>
                                    </el-select>
                                    <el-button style="margin-left: 1%" @click="getTargetChain">查询可供选择的目标区块链</el-button>
                                </el-form-item>
                            <el-form-item label="售卖代币名称">
                                <el-input v-model="createOrderPair.sourceToken" placeholder="售卖代币名称" disabled></el-input>
                            </el-form-item>
                            <el-form-item label="目标代币名称">
                                <el-input v-model="createOrderPair.targetToken" placeholder="想要交换的目标代币名称" disabled></el-input>
                            </el-form-item>
                            <el-form-item>
                                <el-button type="primary" @click="createPair" style="display:block;margin:0 auto">初始化售卖通道</el-button>
                            </el-form-item>
                            </el-form>
                            </div>
                        </el-col>
                        <!-- 创建售卖订单 -->
                        <el-col :span="8">
                            <div class="title" style="text-align: center">创建售卖代币订单</div><br>
                            <div class="CreateOrder">
                                <el-form :model="createSellOrder">
                                <el-form-item :label=this.$data.createOrderPair.sourceToken>
                                    <el-input v-model="createSellOrder.sourceNum" placeholder="售卖代币数量"></el-input>
                                </el-form-item>
                                <el-form-item :label=this.$data.createOrderPair.targetToken>
                                    <el-input v-model="createSellOrder.targetNum" placeholder="1售卖代币=几个目标代币"></el-input>
                                </el-form-item>
                                <el-form-item>
                                    <el-button type="primary" @click="createSell" style="display:block;margin:0 auto">创建售卖代币订单</el-button>
                                </el-form-item>
                                </el-form>
                            </div>
                        </el-col>
                        <!-- 查询余额 -->
                        <el-col :span="8">
                            <div class="title" style="display:inline; padding-left: 30%;">查询账户余额</div>
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
                buyData: [],//购买列表
                sellData: [],//售卖列表
                balanceData: [],//余额列表
                targetChainId: [],//目标链的两个Id值
                options:[],//存目标链名称和channel号,
                label:'',//存储目标链选择框中最终选择的链id,
                createOrderPair: {
                    currentAddress:'',
                    targetChain:'',
                    sourceToken: '',
                    targetToken: ''
                },
                createSellOrder: {
                    sourceNum: '',
                    targetNum: ''
                },
                emptySell:'No Data',
                emptyBuy:'No Data',
                CompanyAFlag: false,
                CompanyBFlag: false
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
                this.$data.createOrderPair.sourceToken = this.getChainByName(data)[2];
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
            this.$data.createOrderPair.targetToken = this.getTokenByCompany(data);
		},
        //创建订单簿
        createPair() {
            this.getChainName().then(data=>{
                let chainId = this.getChainByName(data)[0];
                let chainIdString = chainId.toString();
                //select获取的应为value的值
                // console.log("channel",this.createOrderPair.targetChain);
                this.$axios({
                    method:'get',
                    url:'/api/createOrderPair',
                    params:({    //这里是发送给后台的数据
                        sourceToken:this.$data.createOrderPair.sourceToken,
                        targetToken:this.$data.createOrderPair.targetToken,
                        chain:chainIdString,
                        channel:this.createOrderPair.targetChain
                    })
                }).then((response) =>{    
                    console.log("createOrderR",response);
                    if(response.data.code==200){
                        this.$confirm('创建订单簿成功',{
                        confirmButtonText: '确认',
                        type: 'success'
                        });
                        if(data == "CompanyA" && !this.$data.CompanyAFlag){
                        this.$data.CompanyAFlag = true;
                        this.$axios({
                            method:'get',
                            url:'/api/encourage',
                            params:({    //这里是发送给后台的数据
                                address:this.$store.getters['common/wallet/address'],
                                chain:chainIdString,
                            })
                        }).then((response) =>{    
                            if(response.data.code==200){
                                console.log('获得激励成功');
                            }else{
                                console.log('获得激励失败');
                            }   
                        }).catch((error) =>{
                            console.log('获得激励失败:'+error);
                        })
                        }else if(data == "CompanyB" && !this.$data.CompanyBFlag){
                        this.$data.CompanyBFlag = true;
                        this.$axios({
                            method:'get',
                            url:'/api/encourage',
                            params:({    //这里是发送给后台的数据
                                address:this.$store.getters['common/wallet/address'],
                                chain:chainIdString,
                            })
                        }).then((response) =>{    
                            if(response.data.code==200){
                                console.log('获得激励成功');
                            }else{
                                console.log('获得激励失败');
                            }   
                        }).catch((error) =>{
                            console.log('获得激励失败:'+error);
                        })
                        }
                    }else{
                        this.$confirm('创建订单簿失败: '+response.data.data,{
                            confirmButtonText: '确认',
                            type: 'error'
                        });
                    }   
                }).catch((error) =>{
                    this.$confirm('创建订单簿失败: '+error,{
                    confirmButtonText: '确认',
                    type: 'error'
                    });
                })
            }); 
        },
        //创建售卖代币订单
        createSell() {
            this.getChainName().then(data=>{
                let chain = this.getChainByName(data)[0];
                let chainString = chain.toString();
                this.$axios({
                    method:'get',
                    url:'/api/sendSellOrder',
                    params:({    //这里是发送给后台的数据
                        sourceToken:this.$data.createOrderPair.sourceToken,
                        sourceNum:this.$data.createSellOrder.sourceNum,
                        targetToken:this.$data.createOrderPair.targetToken,
                        price:this.$data.createSellOrder.targetNum,
                        chain:chainString,
                        channel:this.createOrderPair.targetChain
                    })
                }).then((response) =>{  
                    // console.log(response);
                    if(response.data.code==200){
                        this.$confirm('创建售卖代币订单成功',{
                        confirmButtonText: '确认',
                        type: 'success'
                        });
                    }else{
                        this.$confirm('创建售卖代币订单失败：'+response.data.data,{
                        confirmButtonText: '确认',
                        type: 'error'
                        });
                    }    
                }).catch((error) =>{
                    this.$confirm('创建售卖代币订单失败：'+error,{
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
                let chain = this.getChainByName(data)[0];
                let chainString = chain.toString();
                let targetChainName = this.getChainFromCoin(row.targetToken);
                let channel;
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
                            url:'/api/cancelSellOrder',
                            params:({    //这里是发送给后台的数据
                                sourceToken:row.sourceToken,
                                targetToken:row.targetToken,
                                orderId:row.orderId,
                                chain:chainString,
                                channel:channel
                            })
                        }).then((response) =>{  
                            // console.log("cancelSell",response);
                            if(response.data.code==200){
                                this.$confirm('取消订单成功',{
                                confirmButtonText: '确认',
                                type: 'success'
                                });
                                this.sellData.splice(index,1);
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
                let chainString = chain.toString();
                this.$axios({
                    method:'get',
                    url:'/api/listSellOrder',
                    params:({    
                        chain:chainString
                    })
                }).then((response) =>{  
                    // console.log("listSell",response);   
                    let responseData = response.data.data; 
                    if(response.data.code==200){
                        if(responseData.length==0){
                            this.emptySell="当前没有订单"
                        }
                        for (let i = 0; i < responseData.length; i++) {
                            this.sellData.push({
                                orderId:responseData[i].orderId,
                                sourceToken:responseData[i].sourceToken,
                                sourceNum:responseData[i].sourceNum,
                                targetToken:responseData[i].targetToken,
                                price:responseData[i].price
                            })
                        }
                    }else{
                        this.$confirm('查询当前售卖代币的订单失败：'+response.data.msg,{
                        confirmButtonText: '确认',
                        type: 'error'
                        });
                    }  
                }).catch((error) =>{
                    this.$confirm('查询当前售卖代币的订单失败：'+error,{
                        confirmButtonText: '确认',
                        type: 'error'
                    }); 
                })  
            });
        },
        //请求购买的代币
        RefreshForBuy(){
            this.buyData = undefined;
            this.buyData = new Array();
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
                    url:'/api/listBuyOrder',
                    params:({    
                        chain:targetChainString1
                    })
                }).then((response) =>{  
                    console.log("listBuy1",response);   
                    let responseData = response.data.data; 
                    if(response.data.code==200){
                        for (let i = 0; i < responseData.length; i++) {
                            if(data == this.getChainFromCoin(this.Trim(responseData[i].sourceToken))){
                                this.buyData.push({
                                    orderId:responseData[i].orderId,
                                    sourceToken:responseData[i].sourceToken,
                                    sourceNum:responseData[i].sourceNum,
                                    targetToken:responseData[i].targetToken,
                                    price:responseData[i].price
                                })
                            }
                        }
                    }else{
                        this.$confirm('查询当前购买代币的订单失败：'+response.data.msg,{
                        confirmButtonText: '确认',
                        type: 'error'
                        });
                    }  
                }).catch((error) =>{
                    this.$confirm('查询当前购买代币的订单失败：'+error,{
                        confirmButtonText: '确认',
                        type: 'error'
                    }); 
                });
                this.$axios({
                    method:'get',
                    url:'/api/listBuyOrder',
                    params:({    
                        chain:targetChainString2
                    })
                }).then((response) =>{  
                    console.log("listBuy2",response);   
                    let responseData = response.data.data; 
                    if(response.data.code==200){
                        for (let i = 0; i < responseData.length; i++) {
                            if(data == this.getChainFromCoin(this.Trim(responseData[i].sourceToken))){
                                this.buyData.push({
                                    orderId:responseData[i].orderId,
                                    sourceToken:responseData[i].sourceToken,
                                    sourceNum:responseData[i].sourceNum,
                                    targetToken:responseData[i].targetToken,
                                    price:responseData[i].price
                                })
                            }
                        }
                    }else{
                        this.$confirm('查询当前购买代币的订单失败：'+response.data.msg,{
                        confirmButtonText: '确认',
                        type: 'error'
                        });
                    }  
                }).catch((error) =>{
                    this.$confirm('查询当前购买代币的订单失败：'+error,{
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
            this.createOrderPair.currentAddress = this.$store.getters['common/wallet/address'];
            this.initialize();
        }
        

    }
    </script>
    