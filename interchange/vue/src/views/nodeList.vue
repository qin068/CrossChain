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
			</div>

            <!-- 主功能测试节点信息,如果搜索时输入的是区块链名称，则跳转到此页面显示搜索的区块链上，允许本链访问的所有节点信息 -->
            <div class="node">
                <h1 style="float:left">您所查询的{{ blockChain }}链节点信息如下</h1>
                <el-button @click="getNode" style="float:right; margin-left:2%">刷新</el-button>
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
                        label="所在区块链"
                        width="100">
                        <template v-slot="scope">{{ scope.row.blockChain }}</template>
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

		</div>
	</div>
</template>


<script>
import { computed } from "vue";
import topMenu from '../components/top-menu.vue';



export default {
	name: 'company',
	components: {
		topMenu
	},
	data(){
		return {
			background:{
				backgroundImage:'url('+require('../images/head-blue.jpg')+')',
				backgroundRepeat:'no-repeat',
				backgroundSize:'cover',
				width:'100%',
				height:'200px'
			},
            node: [],
            blockChain: ''
		}
	},
	computed: {
		address() {
			return this.$store.getters['common/wallet/address']
		},
        stateColor(){
            return (nodeState) => { 
                if(nodeState=="验证通过"){
                    return {'color':'green'}
                }else{
                    return {'color':'red'}
                }
            }
        }
	},
	methods:{
        //删除字符串前后的空格
        Trim(str){ 
            return str.replace(/(^\s*)|(\s*$)/g, ""); 
        },
        //根据链名称转化为链ID
		getChainId(chainName){
			if(chainName=="mars"){
				return 1;
			}else if(chainName=="venus"){
				return 2;
			}else if(chainName=="earth"){
				return 3;
			}
		},
		//获取当前节点的链名
		getChainName(){
			return this.$axios({
				method:'get',
				url:'/api/selectAllAccount'
			}).then((response) =>{     
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
		// 获取节点信息
		getNode(){
            //获取用户搜索的区块链的名称
            this.blockChain = this.$route.query.companyName;
            this.getChainName().then(data=>{
                //获取当前区块链的名称，以获取此链上收到的所有信息
                let chainId = this.getChainId(data).toString();
                this.$axios({
                    method:'get',
                    url:'/api/listPost',
                    params:({    
                        chain: chainId
                    })
                }).then((response) =>{     
                    if(response.data.code==200){
                        let responseData = response.data.data; 
                        //从当前区块链的所有信息中，筛选出来自用户搜索的链的节点信息
                        for (let i = 0; i < responseData.length; i++) {
                            // console.log(responseData[i]);
                            let arrTitle = this.Trim(responseData[i].title).split("@");
                            //筛选来自用户搜索的链的信息
                            if(arrTitle[0]==this.$route.query.companyName){
                                let arr = this.Trim(responseData[i].content).split("@");
                                let nodeID = arr[0];
                                let nodeName = arrTitle[1];
                                let nodeLocation = arr[1];
                                let nodeState = arr[2];
                                //如果信息来自用户搜索的链，则去获取hash值等信息
                                this.$axios({
                                    method:'get',
                                    url:'/api/getTitleById',
                                    params:({    
                                        title: this.Trim(responseData[i].title)
                                    })
                                }).then((response)=>{
                                    if(response.data.code==200){
                                        if(response.data.data!=null){
                                            let responseData = response.data.data; 
                                            let nodeHeight = responseData.height;
                                            let nodeHash = responseData.txhash;
                                            let nodeTime = responseData.timestamp;
                                            this.node.push({
                                                nodeID: nodeID, 
                                                blockChain: arrTitle[0],
                                                nodeName: nodeName, 
                                                nodeLocation: nodeLocation, 
                                                nodeState: nodeState, 
                                                nodeHeight: nodeHeight, 
                                                nodeHash: nodeHash, 
                                                nodeTime: nodeTime,
                                            });
                                        }
                                    }
                                });
                                
                            }
                            
                            
                        }
                    }else{
                        this.$confirm('获取节点数据过程失败  '+response.data.msg,{
                        confirmButtonText: 'OK',
                        type: 'error'
                        });
                    }   
                }).catch((error) =>{
                    this.$confirm('获取节点数据过程失败  '+error,{
                    confirmButtonText: 'OK',
                    type: 'error'
                    });
                })   
            });
		},
	},
	mounted(){
		this.getNode();
	}
}
</script>
