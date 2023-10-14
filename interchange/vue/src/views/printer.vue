<style scoped>
    .search{
        margin-top: 5%;
        margin-left: 33%;
    }

    .node{
        margin-top: 2%;
        vertical-align: middle;
    }

</style>
<template>
    <el-container >
	<el-header>
		<topMenu></topMenu>
	</el-header>
	<el-container>
		<el-aside width="200px" style="margin-left:20px; margin-top: 40px;">
			<sideMenu></sideMenu>
		</el-aside>
		<el-main height="1000px">
			<!-- 系统名称和搜索图标 -->
			<div :style="background" style="padding-top:1px; margin-top: 2%;">
				<div class="search">
                    <el-input
                        placeholder="在此输入打印机名称或公司名称进行查询"
                        v-model="input"
                        style="width: 44%"
                    >
                    </el-input>
                    <el-button @click="search" type="primary">查询打印机信息</el-button>
                </div>
			</div>

            <!-- 当前区块链打印机信息 -->
            <div class="node">
                <h1 style="float:left;">当前区块链打印机信息</h1>
                <el-button @click="getOwn" style="float:right;" >刷新</el-button>
                <el-table
                    ref="multipleTable"
                    :data="blogSent"
                    tooltip-effect="dark"
                    border
                    stripe
                    style="width: 100%; margin-top:1%"
                >
                    <el-table-column
                        label="打印机名称"
                        width="180">
                        <template v-slot="scope">{{ scope.row.name }}</template>
                    </el-table-column>
                    <el-table-column
                        label="所属公司"
                        width="180">
                        <template v-slot="scope">{{ scope.row.company }}</template>
                    </el-table-column>
                    <el-table-column
                        label="拥有查看权限的区块链"
                        width="180">
                    <template v-slot="scope">{{ scope.row.targetCompany }}</template>
                    </el-table-column>
                    <el-table-column
                        label="品牌"
                        width="180">
                    <template v-slot="scope">{{ scope.row.brand }}</template>
                    </el-table-column>
                    <el-table-column
                        label="型号"
                        width="180">
                     <template v-slot="scope">{{ scope.row.model }}</template>
                    </el-table-column>
                    <el-table-column
                        label="打印类型"
                        width="180">
                     <template v-slot="scope">{{ scope.row.type }}</template>
                    </el-table-column>
                    <el-table-column
                        label="上市时间"
                        width="180">
                     <template v-slot="scope">{{ scope.row.time }}</template>
                    </el-table-column>
                    <el-table-column
                        label="区块高度"
                        width="180">
                    <template v-slot="scope">{{ scope.row.height}}</template>
                    </el-table-column>
                    <el-table-column
                        label="节点哈希值"
                        width="380">
                    <template v-slot="scope">{{ scope.row.hash }}</template>
                    </el-table-column>
                    <el-table-column
                        label="节点时间戳"
                        width="180">
                    <template v-slot="scope">{{ scope.row.timestamp }}</template>
                    </el-table-column>
                    <el-table-column fixed="right" label="操作">
                        <template v-slot="scope">
                        <el-button
                            type="primary"
                            @click="checkDetail(scope.row)">查看详细信息</el-button>
                        </template>
                    </el-table-column>
                </el-table>
			</div>

            <!-- 当前接收到的打印机信息 -->
            <div class="node">
                <h1 style="float:left;">当前接收到的打印机信息</h1>
                <el-button @click="getTarget" style="float:right;" >刷新</el-button>
                <el-table
                    ref="multipleTable"
                    :data="blogGet"
                    tooltip-effect="dark"
                    border
                    stripe
                    style="width: 100%; margin-top:1%"
                >
                    <el-table-column
                        label="打印机名称"
                        width="180">
                        <template v-slot="scope">{{ scope.row.name }}</template>
                    </el-table-column>
                    <el-table-column
                        label="所属公司"
                        width="180">
                        <template v-slot="scope">{{ scope.row.company }}</template>
                    </el-table-column>
                    <el-table-column
                        label="品牌"
                        width="180">
                    <template v-slot="scope">{{ scope.row.brand }}</template>
                    </el-table-column>
                    <el-table-column
                        label="型号"
                        width="180">
                     <template v-slot="scope">{{ scope.row.model }}</template>
                    </el-table-column>
                    <el-table-column
                        label="打印类型"
                        width="180">
                     <template v-slot="scope">{{ scope.row.type }}</template>
                    </el-table-column>
                    <el-table-column
                        label="上市时间"
                        width="180">
                     <template v-slot="scope">{{ scope.row.time }}</template>
                    </el-table-column>
                    <el-table-column
                        label="区块高度"
                        width="180">
                    <template v-slot="scope">{{ scope.row.height }}</template>
                    </el-table-column>
                    <el-table-column
                        label="节点哈希值"
                        width="380">
                    <template v-slot="scope">{{ scope.row.hash }}</template>
                    </el-table-column>
                    <el-table-column
                        label="节点时间戳"
                        width="180">
                    <template v-slot="scope">{{ scope.row.timestamp }}</template>
                    </el-table-column>
                    <el-table-column fixed="right" label="操作">
                        <template v-slot="scope">
                        <el-button
                            type="primary"
                            @click="checkDetail(scope.row)">查看详细信息</el-button>
                        </template>
                    </el-table-column>
                </el-table>
			</div>

            <!-- 查询结果 -->
            <div class="node" v-show="searchFlag">
                <h1>查询结果</h1>
                <el-table
                    ref="multipleTable"
                    :data="searchResult"
                    tooltip-effect="dark"
                    border
                    stripe
                    style="width: 100%; margin-top:1%"
                >
                    <el-table-column
                        label="打印机名称"
                        width="180">
                        <template v-slot="scope">{{ scope.row.name }}</template>
                    </el-table-column>
                    <el-table-column
                        label="所属公司"
                        width="180">
                        <template v-slot="scope">{{ scope.row.company }}</template>
                    </el-table-column>
                    <el-table-column
                        label="品牌"
                        width="180">
                    <template v-slot="scope">{{ scope.row.brand }}</template>
                    </el-table-column>
                    <el-table-column
                        label="型号"
                        width="180">
                     <template v-slot="scope">{{ scope.row.model }}</template>
                    </el-table-column>
                    <el-table-column
                        label="打印类型"
                        width="180">
                     <template v-slot="scope">{{ scope.row.type }}</template>
                    </el-table-column>
                    <el-table-column
                        label="上市时间"
                        width="180">
                     <template v-slot="scope">{{ scope.row.time }}</template>
                    </el-table-column>
                    <el-table-column
                        label="区块高度"
                        width="180">
                    <template v-slot="scope">{{ scope.row.height }}</template>
                    </el-table-column>
                    <el-table-column
                        label="节点哈希值"
                        width="380">
                    <template v-slot="scope">{{ scope.row.hash }}</template>
                    </el-table-column>
                    <el-table-column
                        label="节点时间戳"
                        width="180">
                    <template v-slot="scope">{{ scope.row.timestamp }}</template>
                    </el-table-column>
                    <el-table-column fixed="right" label="操作">
                        <template v-slot="scope">
                        <el-button
                            type="primary"
                            @click="checkDetail(scope.row)">查看详细信息</el-button>
                        </template>
                    </el-table-column>
                </el-table>
			</div>

		</el-main>
	</el-container>
	</el-container>

</template>


<script>
import topMenu from '../components/top-menu.vue';
import sideMenu from '../components/side-menu.vue';

export default {
	name: 'company',
	components: {
		topMenu,
        sideMenu
	},
	data(){
		return {
            //背景图片控制
			background:{
				backgroundImage:'url('+require('../images/head-blue.jpg')+')',
				backgroundRepeat:'no-repeat',
				backgroundSize:'cover',
				width:'100%',
				height:'220px'
			},
            input:'',//搜索输入框
            blogSent: [],//主功能-本链打印机信息
            blogGet: [],//主功能-接收到的打印机信息
            searchFlag: false,//是否显示查询结果,
            searchResult:[]
		}
	},
	computed: {
        //钱包操作-获取当前区块链地址
		address() {
			return this.$store.getters['common/wallet/address']
		},
	},
	methods:{
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
        //根据链名转化为链ID和公司名
        getChainByName(chainName){
            if(chainName=="CompanyA"){
				return [1,"CompanyA"];
			}else if(chainName=="CompanyB"){
				return [2,"CompanyB"];
			}else if(chainName=="CompanyC"){
				return [3,"CompanyC"];
			}
        },
        //获取打印机信息（链+数据库）
        getOwn(){ 
            //清空“测试节点”列表中原有的内容
            this.blogSent = undefined;
			this.blogSent = new Array();

            this.getChainName().then(data=>{
                let chainId = this.getChainByName(data)[0].toString();
                this.$axios({
                    method:'get',
                    url:'/api/listSendPost',
                    params:({    
                        chain: chainId
                    })
                }).then((response) =>{     
                    // console.log(response);
                    if(response.data.code==200){
                        let responseData = response.data.data; 
                        for (let i = 0; i < responseData.length; i++) {
                            //获取数据库中存储的hash值等信息
                            let arrTitle = this.Trim(responseData[i].title).split("@");
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
                                        let arr = responseData.content.split("@"); 
                                        //将信息推入到自己发送列表中
                                        this.blogSent.push({
                                            title: responseData.title,
                                            name: arrTitle[1],
                                            company: arrTitle[0],
                                            targetCompany: arrTitle[2],
                                            brand: arr[0],
                                            model: arr[1],
                                            type: arr[2],
                                            time: arr[3],
                                            height: responseData.height,
                                            hash: responseData.txhash,
                                            timestamp: responseData.timestamp
                                        });
                                    }
                                }
                            });
                        }
                    }else{
                        this.$confirm('获取当前区块链打印机信息过程失败  '+response.data.msg,{
                        confirmButtonText: 'OK',
                        type: 'error'
                        });
                    }   
                }).catch((error) =>{
                    this.$confirm('获取当前区块链打印机信息过程失败  '+error,{
                    confirmButtonText: 'OK',
                    type: 'error'
                    });
                })
            });
        },
        //获得得他链消息
        getTarget(){
            this.blogGet = undefined;
			this.blogGet = new Array();
            this.getChainName().then(data=>{
                let chainId = this.getChainByName(data)[0].toString();
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
                            if(arrTitle[0] != "blog"){
                                let arr = responseData[i].content.split("@");
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
                                            //将信息推入到自己发送列表中
                                            this.blogGet.push({
                                                title: responseData.title,
                                                name: arrTitle[1],
                                                company: arrTitle[0],
                                                brand: arr[0],
                                                model: arr[1],
                                                type: arr[2],
                                                time: arr[3],
                                                height: responseData.height,
                                                hash: responseData.txhash,
                                                timestamp: responseData.timestamp
                                            });
                                        }
                                    }
                                });
                            }

                        }
                    }else{
                        this.$confirm('获取当前接收到的打印机信息过程失败  '+response.data.msg,{
                        confirmButtonText: 'OK',
                        type: 'error'
                        });
                    }   
                }).catch((error) =>{
                    this.$confirm('获取当前接收到的打印机信息过程失败  '+error,{
                    confirmButtonText: 'OK',
                    type: 'error'
                    });
                })
            });
        },
        //点击最新交易列表中的详情按钮，跳转页面，使用trade的id号作为索引值
        checkDetail(row){
            this.$router.push({
                path: "/printerDetail",
                query: { title: row.title}
            });
        },
        searchForBase(title,arrTitle,arr){
            this.$axios({
                method:'get',
                url:'/api/getTitleById',
                params:({    
                    title: title
                })
            }).then((response)=>{
                // console.log(response);
                if(response.data.code==200){
                    if(response.data.data!=null){
                        let responseData = response.data.data; 
                        //将信息推入到自己发送列表中
                        this.searchResult.push({
                            title: responseData.title,
                            name: arrTitle[1],
                            company: arrTitle[0],
                            brand: arr[0],
                            model: arr[1],
                            type: arr[2],
                            time: arr[3],
                            height: responseData.height,
                            hash: responseData.txhash,
                            timestamp: responseData.timestamp
                        });
                    }
                }
            });
        },
        //查询节点的最新交易信息
        search(){
            this.searchResult = undefined;
			this.searchResult = new Array();
            this.getChainName().then(data=>{
                //获取本链上可访问的节点信息（由其余两链授权）
                let chainId = this.getChainByName(data)[0].toString();
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
                        if(responseData.length!=0){
                            //则1.判断是否有符合输入值的数据
                            //判断是公司名称还是打印机名称
                            if(this.input == "CompanyA" || this.input == "CompanyB" || this.input == "CompanyC"){
                                for(let i=0; i<responseData.length; i++){
                                    let arrTitle = this.Trim(responseData[i].title).split("@");
                                    let arr = responseData[i].content.split("@");
                                    if(arrTitle[0]==this.input){
                                        this.searchFlag = true;
                                        let title = this.Trim(responseData[i].title);
                                        this.searchForBase(title,arrTitle,arr);
                                    }
                                    
                                }
                            }else{
                                for(let i=0; i<responseData.length; i++){
                                    let arrTitle = this.Trim(responseData[i].title).split("@");
                                    let arr = responseData[i].content.split("@");
                                    if(arrTitle[1]==this.input){
                                        this.searchFlag = true;
                                        let title = this.Trim(responseData[i].title);
                                        this.searchForBase(title,arrTitle,arr);
                                    }
                                }
                            }
                        }else{
                            this.$confirm('您所在链上未被授权访问任何打印机信息',{
                                confirmButtonText: 'OK',
                                type: 'info'
                            });
                        }
                    }else{
                        this.$confirm('获取查询结果过程中出现错误  '+response.data.msg,{
                            confirmButtonText: 'OK',
                            type: 'error'
                        });
                    }   
                }).catch((error) =>{
                        this.$confirm('获取查询结果过程中出现错误  '+error,{
                        confirmButtonText: 'OK',
                        type: 'error'
                    });
                })
            });
        }
	},
	mounted(){
        if(this.$store.getters['common/wallet/address'] != ""){
            this.getOwn();
            this.getTarget();
        }
	}
}
</script>
