<style scoped>
	.top{
		color:white; 
		font-size:32px; 
		font-weight:bolder; 
		padding-top:80px;
		text-align: center;
	}

    .search{
        margin-top: 5%;
        margin-left: 33%;

    }

    .node{
        margin-top: 5%;
        vertical-align: middle;
    }

    .trade{
        margin-top: 5%;
    }

</style>
<template>
	<div>
		<div class="container">
			<!-- 顶部图标和菜单 -->
			<topMenu></topMenu>

			<!-- 系统名称和搜索图标 -->
			<div :style="background">
				<div class="top">Tses区块链系统互联互通平台</div>
				<div class="search">
                    <el-input
                        placeholder="在此输入节点名称或区块链名称进行查询"
                        v-model="input"
                        style="width: 44%"
                    >
                    </el-input>
                    <el-button icon="el-icon-search" @click="search"></el-button>
                </div>
			</div>

            <!-- 主功能测试节点信息 -->
            <div class="node">
                <h1 style="float:left;">测试节点</h1>
                <el-button @click="getNode2" style="float:right;" >刷新</el-button>
                <el-table
                    ref="multipleTable"
                    :data="node"
                    tooltip-effect="dark"
                    border
                    stripe
                    style="width: 100%; margin-top:1%"
                >
                    <el-table-column
                        label="节点ID"
                        width="100">
                        <template v-slot="scope">{{ scope.row.nodeID }}</template>
                    </el-table-column>
                    <el-table-column
                        label="目标区块链"
                        width="100">
                        <template v-slot="scope">{{ scope.row.targetChain }}</template>
                    </el-table-column>
                    <el-table-column
                        label="节点名称"
                        width="150">
                    <template v-slot="scope">{{ scope.row.nodeName }}</template>
                    </el-table-column>
                    <el-table-column
                        label="节点位置"
                        width="200">
                    <template v-slot="scope">{{ scope.row.nodeLocation }}</template>
                    </el-table-column>
                    <el-table-column
                        label="状态"
                        width="100">
                    <template v-slot="scope">
                        <div v-html="scope.row.nodeState" :style="stateColor2(scope.row.nodeState)"></div>
                    </template>
                    </el-table-column>
                    <el-table-column
                        label="区块高度"
                        width="100">
                    <template v-slot="scope">{{ scope.row.nodeHeight }}</template>
                    </el-table-column>
                    <el-table-column
                        label="节点哈希值"
                        width="380">
                    <template v-slot="scope">{{ scope.row.nodeHash }}</template>
                    </el-table-column>
                    <el-table-column
                        label="节点时间戳"
                    >
                    <template v-slot="scope">{{ scope.row.nodeTime }}</template>
                    </el-table-column>
                </el-table>
			</div>

			<!-- 数据库中原节点信息 -->
			<div class="node">
                <h1>数据库中原节点</h1>
                <el-table
                    ref="multipleTable"
                    :data="tableData"
                    tooltip-effect="dark"
                    border
                    stripe
                    style="width: 100%; margin-top:1%"
                >
                    <el-table-column
                        label="节点ID"
                        width="200">
                        <template v-slot="scope">{{ scope.row.nodeID }}</template>
                    </el-table-column>
                    <el-table-column
                        label="节点名称"
                        width="150">
                    <template v-slot="scope">{{ scope.row.nodeName }}</template>
                    </el-table-column>
                    <el-table-column
                        label="节点位置"
                        width="200">
                    <template v-slot="scope">{{ scope.row.nodeLocation }}</template>
                    </el-table-column>
                    <el-table-column
                        label="状态"
                        width="100">
                    <template v-slot="scope">
                        <div v-html="scope.row.nodeState" :style="stateColor(scope.row.nodeState)"></div>
                    </template>
                    </el-table-column>
                    <el-table-column
                        label="区块高度"
                        width="100">
                    <template v-slot="scope">{{ scope.row.nodeHeight }}</template>
                    </el-table-column>
                    <el-table-column
                        label="最新区块哈希值"
                        width="380">
                    <template v-slot="scope">{{ scope.row.nodeHash }}</template>
                    </el-table-column>
                    <el-table-column
                        label="最新区块时间"
                    >
                    <template v-slot="scope">{{ scope.row.nodeTime }}</template>
                    </el-table-column>
                </el-table>
			</div>

            <!-- 交易信息 -->
            <div class="trade">
                <h1>最新交易</h1>
                <el-table
                    ref="multipleTable"
                    :data="tradeTable"
                    tooltip-effect="dark"
                    border
                    stripe
                    style="width: 100%; margin-top:1%"
                >
                    <el-table-column
                        label="哈希值"
                        width="380">
                        <template v-slot="scope">{{ scope.row.tradeHash }}</template>
                    </el-table-column>
                    <el-table-column
                        label="区块高度"
                        width="100">
                    <template v-slot="scope">{{ scope.row.tradeHeight }}</template>
                    </el-table-column>
                    <el-table-column
                        label="业务类型"
                        width="100">
                    <template v-slot="scope">{{ scope.row.tradeType }}</template>
                    </el-table-column>
                    <el-table-column
                        label="信息IP"
                        width="150">
                    <template v-slot="scope">{{ scope.row.tradeIP }}</template>
                    </el-table-column>
                    <el-table-column
                        label="时间戳"
                        width="200">
                    <template v-slot="scope">{{ scope.row.tradeTime }}</template>
                    </el-table-column>
                    <el-table-column
                        label="验证状态"
                        width="200">
                    <template v-slot="scope">{{ scope.row.tradeState }}</template>
                    </el-table-column>
                    <el-table-column
                    fixed="right"
                    label="">
                    <template v-slot="scope">
                            <el-button type="primary" @click="tradeDetail(scope.$index, scope.row)">详情</el-button>
                    </template>
                    </el-table-column>
                </el-table>
                
            </div>
		</div>

	</div>

</template>


<script>
import { computed } from "vue";
import topMenu from '../components/top-menu.vue';
import { LengthOp } from '../store/generated/cosmos/cosmos-sdk/ibc.core.connection.v1/module/types/confio/proofs';


export default {
	name: 'company',
	components: {
		topMenu
	},
	data(){
		return {
            //背景图片控制
			background:{
				backgroundImage:'url('+require('../images/head-blue.jpg')+')',
				backgroundRepeat:'no-repeat',
				backgroundSize:'cover',
				width:'100%',
				height:'330px'
			},
            input:'',//搜索输入框
            tableData: [],//数据库中原节点列表
            tradeTable: [],//数据库中最新交易列表
            node: [],//主功能-测试节点列表
            targetChainId: [],//目标链的两个Id值
            searchToNodeDetail:[],//搜索功能-传递到nodeDetail页面的信息，包括每个节点的拼接title和content
		}
	},
	computed: {
        //钱包操作-获取当前区块链地址
		address() {
			return this.$store.getters['common/wallet/address']
		},
        //“测试节点”列表中，状态栏文字颜色控制
        stateColor2(){
            return (nodeState) => { 
                if(nodeState=="验证通过"){
                    return {'color':'green'}
                }else{
                    return {'color':'red'}
                }
            }
        },
        //“数据库原节点”列表中，状态栏文字颜色控制
        stateColor(){
            return (nodeState) => { 
                if(nodeState=="数据已同步"){
                    return {'color':'green'}
                }else{
                    return {'color':'red'}
                }
            }
        }
	},
	methods:{
        //去除字符串前后的空格（cosmos返回的字符串前总会多一个空格）
        Trim(str){ 
            return str.replace(/(^\s*)|(\s*$)/g, ""); 
        },
        //根据链名转化为链ID
        getChainIdByName(chainName){
            if(chainName=="mars"){
				return 1;
			}else if(chainName=="venus"){
				return 2
			}else if(chainName=="earth"){
				return 3;
			}
        },
        //根据原始链名转化为Company的用户可见链名
        getCompanyByChainName(chainName){
			if(chainName=="mars"){
				return "CompanyA";
			}else if(chainName=="venus"){
				return "CompanyB";
			}else if(chainName=="earth"){
				return "CompanyC";
			}
		},
        //根据链名称获取目标链ID
		getChainId(chainName){
			if(chainName=="mars"){
                this.targetChainId[0] = 2;
                this.targetChainId[1] = 3;
			}else if(chainName=="venus"){
				this.targetChainId[0] = 1;
                this.targetChainId[1] = 3;
			}else if(chainName=="earth"){
				this.targetChainId[0] = 1;
                this.targetChainId[1] = 2;
			}
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
        //获取目标链（他链）收获到的节点信息（链+数据库）
        getTargetChainContent(chainId,data){
            this.$axios({
                method:'get',
                url:'/api/listPost',
                params:({    
                    chain: chainId//传参为他链的ID值,类型为int.toString
                })
            }).then((response) =>{     
                // console.log(response);
                if(response.data.code==200){
                    let responseData = response.data.data; 
                    for (let i = 0; i < responseData.length; i++) {
                        let arrTitle = this.Trim(responseData[i].title).split("@");
                        //如果当前链名==发送消息方
                        if(arrTitle[0]==this.getCompanyByChainName(data)){
                            let arr = responseData[i].content.split("@");
                            let nodeID = arr[0];
                            let nodeName = arrTitle[1];
                            let targetChain = arrTitle[2];
                            let nodeLocation = arr[1];
                            let nodeState = arr[2];
                            //获取数据库中存储的hash值等信息
                            this.$axios({
                                method:'get',
                                url:'/api/getTitleById',
                                params:({    
                                    title: this.Trim(responseData[i].title)
                                })
                            }).then((response)=>{
                                // console.log(response);
                                if(response.data.code==200){
                                    if(response.data.data!=null){
                                        let responseData = response.data.data; 
                                        let nodeHeight = responseData.height;
                                        let nodeHash = responseData.txhash;
                                        let nodeTime = responseData.timestamp;
                                        //将信息推入到节点列表中
                                        this.node.push({
                                            nodeID: nodeID, 
                                            nodeName: nodeName, 
                                            nodeLocation: nodeLocation, 
                                            nodeState: nodeState, 
                                            nodeHeight: nodeHeight, 
                                            nodeHash: nodeHash, 
                                            nodeTime: nodeTime,
                                            targetChain: targetChain
                                        });
                                    }
                                }
                            });
                        }
                    }
                }else{
                    this.$confirm('获取节点信息过程失败  '+response.data.msg,{
                    confirmButtonText: 'OK',
                    type: 'error'
                    });
                }   
            }).catch((error) =>{
                this.$confirm('获取节点信息过程失败  '+error,{
                confirmButtonText: 'OK',
                type: 'error'
                });
            })
        },
		// 获取节点信息-读链+数据库，三链通信需读另外两条链才可读全
		getNode2(){
            //清空“测试节点”列表中原有的内容
            this.node = undefined;
			this.node = new Array();
            //获取当前区块链的名字
            this.getChainName().then(data=>{
                this.getChainId(data);//获取目标区块链的ID
                //读取第一条他链获得的信息（由于/listSendPost获取到的数据中不包含content内容，不能完整显示，因此只能读取另外两条链/listPost的信息）
                this.getTargetChainContent(this.targetChainId[0].toString(),data);
                //读取第二条链获得的信息
                this.getTargetChainContent(this.targetChainId[1].toString(),data);
            });
		},
        //获取数据库中company_node表中存储的信息
        getNode(){
            this.$axios({
				method:'get',
				url:'/api/selectAllCompanyNode',
			}).then((response) =>{     
				// console.log(response);
				if(response.data.code==200){
				  	let responseData = response.data.data; 
                    for (let i = 0; i < responseData.length; i++) {
                        let nodeID = responseData[i].nd_code;
                        let nodeName = responseData[i].nd_name;
                        let nodeLocation = responseData[i].nd_location;
                        let nodeState = responseData[i].nd_state;
                        let nodeHeight = responseData[i].nd_height;
                        let nodeHash = responseData[i].nd_hash;
                        let nodeTime = responseData[i].nd_timestamp;
                        this.tableData.push({
                            nodeID: nodeID, 
                            nodeName: nodeName, 
                            nodeLocation: nodeLocation, 
                            nodeState: nodeState, 
                            nodeHeight: nodeHeight, 
                            nodeHash: nodeHash, 
                            nodeTime: nodeTime
                        });
                    }
				}else{
				  this.$confirm(response.data.msg,{
				    confirmButtonText: 'OK',
				    type: 'error'
				  });
				}   
			}).catch((error) =>{
				this.$confirm(error,{
				confirmButtonText: 'OK',
				type: 'error'
				});
			})
        },
        // 获取数据库中的最新交易信息
		getTrade(){
			this.$axios({
				method:'get',
				url:'/api/selectAllCompanyTrade',
			}).then((response) =>{     
				// console.log(response);
				if(response.data.code==200){
				  	let responseData = response.data.data; 
                    for (let i = 0; i < responseData.length; i++) {
                        this.tradeTable.push({
                            id: responseData[i].id, 
                            tradeHash: responseData[i].td_hash, 
                            tradeHeight: responseData[i].td_height, 
                            tradeType: responseData[i].td_type, 
                            tradeIP: responseData[i].td_ip, 
                            tradeTime: responseData[i].td_timestamp, 
                            tradeState: responseData[i].td_valid, 
                        });
                    }
				}else{
				  this.$confirm('获取交易数据失败  '+response.data.msg,{
                        confirmButtonText: 'OK',
                        type: 'error'
				  });
				}   
			}).catch((error) =>{
				this.$confirm('获取交易数据失败  '+error,{
                    confirmButtonText: 'OK',
                    type: 'error'
				});
			})
		},

        //点击最新交易列表中的详情按钮，跳转页面，使用trade的id号作为索引值
        tradeDetail(index,row){
            this.$router.push({
                path: "/tradeDetail",
                query: { id: row.id }
            });
        },

        //查询节点的最新交易信息
        search(){
            //如果输入链名则查询该链允许其访问的所有信息
            if(this.input == "CompanyA" || this.input == "CompanyB" || this.input == "CompanyC"){
                this.$router.push({
                    path: "/nodeList",
                    query: { 
                        companyName: this.input,
                    }
                });
            }else{
                //否则查询其余两链允许它访问到的节点信息
                this.getChainName().then(data=>{
                    //获取本链上可访问的节点信息（由其余两链授权）
                    let chainId = this.getChainIdByName(data).toString();
                    this.$axios({
                        method:'get',
                        url:'/api/listPost',
                        params:({    
                            chain:chainId
                        })
                    }).then((response) =>{     
                        // console.log(response);
                        if(response.data.code==200){
                            let responseData = response.data.data; 
                            //如果该链上有数据，
                            if(responseData!=null){
                                //则1.判断是否有符合输入值的数据
                                for(let i=0; i<responseData.length; i++){
                                    let arrTitle = this.Trim(responseData[i].title).split("@");
                                    if(arrTitle[1]==this.input){
                                        //2.符合则将title和content放入数组
                                        this.searchToNodeDetail.push({
                                            title: this.Trim(responseData[i].title),
                                            content: this.Trim(responseData[i].content)
                                        });
                                    }
                                }
                                //3.将整个数组传值到nodeDetail页面
                                let search = encodeURIComponent(JSON.stringify(this.searchToNodeDetail));
                                this.$router.push({
                                    path: "/nodeDetail",
                                    query: { 
                                        search: search
                                    }
                                });
                            }else{
                                this.$confirm('您所在链上未被授权任何节点信息',{
                                    confirmButtonText: 'OK',
                                    type: 'error'
                                });
                            }
                        }else{
                            this.$confirm('获取节点信息过程中出现错误  '+response.data.msg,{
                                confirmButtonText: 'OK',
                                type: 'error'
                            });
                        }   
                    }).catch((error) =>{
                            this.$confirm('获取节点信息过程中出现错误  '+error,{
                            confirmButtonText: 'OK',
                            type: 'error'
                        });
                    })
                });
            }
        }
	},
	mounted(){
		this.getNode();
        this.getTrade();
        this.getNode2();
	}
}
</script>
