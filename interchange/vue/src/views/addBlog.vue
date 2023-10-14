<style scoped>
	.addNode{
		background-color: white;
		height: 700px;
		width: 45%;
		margin-left: 30%;
		border-radius: 10px;
		text-align: center;
		vertical-align: middle;
		padding: 2%;
        margin-top:2%
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
			
            <!-- 发布博客 -->
            <div class="addNode">
                <h1>发布新博客</h1>
                <el-form ref="form" :model="blog" label-width="100px" style="margin-top:10%">
                    <el-form-item label="链上地址">
                        <el-input v-model="blog.address"></el-input>
                    </el-form-item>
                    <el-form-item label="所属区块链">
                        <el-input v-model="blog.company" style="float:left; width:50%" :disabled="true"></el-input>
                        <el-button @click="getCompany" style="float:left;">查询当前账户所在区块链名称</el-button>
                    </el-form-item>
                    <el-form-item label="博客标题">
                        <el-input v-model="blog.title" placeholder="只支持短标题（10个字符以内）"></el-input>
                    </el-form-item>
                    <el-form-item label="图片链接">
                        <el-input v-model="blog.img" placeholder="只支持在线图片链接"></el-input>
                    </el-form-item>
                    <el-form-item label="主要内容">
                        <el-input v-model="blog.text" :rows="3" type="textarea" placeholder="主要内容，无法换行或空格"></el-input>
                    </el-form-item>
                    <el-form-item label="当前地理位置">
                        <el-input v-model="blog.location" placeholder="省份，例如河北省，天津市"></el-input>
                    </el-form-item>
                    <el-button type="primary" @click="addBlog" style="width:150px">发布新博客</el-button>
                </el-form>
            </div>
			
		</el-main>
	</el-container>
	</el-container>
</template>


<script>
import topMenu from '../components/top-menu.vue';
import sideMenu from "../components/side-menu.vue";


export default {
	name: 'company',
	components: {
		topMenu,
		sideMenu
	},
	data(){
		return {
			blog: {
				address: '',
                company: '',
				title: '',
				img:'',
				text:'',
				location:'',
			}
		}
	},
	computed: {
		address() {
			return this.$store.getters['common/wallet/address']
		}
	},
	methods:{
		//去除字符串前后的空格（cosmos返回的字符串前总会多一个空格）
        Trim(str){ 
            return str.replace(/(^\s*)|(\s*$)/g, ""); 
        },
		//获取当前区块链地址
		getAddress(){
			this.blog.address = this.$store.getters['common/wallet/address'];
			this.getChainName().then(data=>{
				this.blog.company = this.getCompanyByChainName(data);
			});
		},
		// //由原区块链名称获取用户看到的Company名称
		// getCompanyByChainName(chainName){
		// 	if(chainName=="mars"){
		// 		return "CompanyA";
		// 	}else if(chainName=="venus"){
		// 		return "CompanyB";
		// 	}else if(chainName=="earth"){
		// 		return "CompanyC";
		// 	}
		// },
		//根据链名称转化为链ID
		getChainId(chainName){
			if(chainName=="CompanyA"){
				return 1;
			}else if(chainName=="CompanyB"){
				return 2
			}else if(chainName=="CompanyC"){
				return 3;
			}
		},
		//获取当前节点的ID值
		getChainName(){
			return this.$axios({
				method:'get',
				url:'/api/selectAllAccount'
			}).then((response) =>{     
				// console.log(response);
				if(response.data.code==200){
                    let responseData = response.data.data; 
					for(let i=0; i<responseData.length; i++){
						if(this.blog.address==responseData[i].address){
							return responseData[i].chain;
						}
					}
				}
			}).catch((error) =>{
				alert(error);
			})
		},
        // 默认都放在mars链上
		// 获取通信目标链,先筛选出port是blog的channel，然后看from是否是本链，是则放入to和channelId
		getCompany(){
			this.getChainName().then(data=>{
				if(this.$data.blog.company == "" || this.$data.blog.company == undefined){
					this.$data.blog.company = data;
				}
			});
		},
		// 新增节点信息
        sendMessage(title,body,chain){
            //本链上链
            this.$axios({
                method:'get',
                url:'/api/sendMessage',
                params:({    
                    title:title,
                    body:body,
                    chain:chain,
                })
            }).then((response) =>{     
                // console.log(response);
                if(response.data.code==200){
                    this.$confirm('博客发布成功',{
                        confirmButtonText: '确认',
                        type: 'success'
                    });
                }else{
                    this.$confirm('博客发布失败：'+ response.data.data,{
                    confirmButtonText: '确认',
                    type: 'error'
                    });
                }   
            }).catch((error) =>{
                this.$confirm('博客发布失败：'+error,{
                confirmButtonText: '确认',
                type: 'error'
                });
            })
        },
        // sendPost(titleToChain,content,chainIdString,channel){
        //     //将节点信息上链
            
        // },
        getChannel(data){
            return this.$axios({
                method:'get',
                url:'/api/selectAllChannel'
            }).then((response) =>{     
                console.log(response);
                if(response.data.code==200){
                    let responseData = response.data.data; 
                    for(let i=0; i<responseData.length; i++){
                        //如果连接功能是blog
                        if(responseData[i].port == "blog"){
                            //如果发送链是本链
                            if(responseData[i].from == data){
                                if(responseData[i].to == "CompanyA"){
                                    return responseData[i].channelId;
                                }
                            }
                        }
                    }
                }
            }).catch((error) =>{
                alert(error);
            })
        },
		addBlog(){
			//获取所在链
			this.getChainName().then(data=>{
                //预处理数据
                let chainId= this.getChainId(data);
				let chainIdString = chainId.toString();

                //如果是在mars链上就调用单链通信协议，否则调用跨链通信协议
                if(data == "CompanyA"){
                    let title = this.$data.blog.company + '@' + this.$data.blog.title;//单链-拼接传入的title内容 公司名称+标题 
				    let content = this.$data.blog.img + '@' + this.$data.blog.text + '@' + this.$data.blog.location;//拼接传入的content内容 图片+文本+地点
                    this.sendMessage(title, content, chainIdString);
                }else{
                    //跨链-拼接传入的title内容 blog标识+公司名称+标题
                    let titleToChain = 'blog' + '@' + this.$data.blog.company + '@' + this.$data.blog.title;
                    let content = this.$data.blog.img + '@' + this.$data.blog.text + '@' + this.$data.blog.location;
                    let contentToChain = content.substring(0,10);
                    this.getChannel(data).then(channel => {
                        this.$axios({
                            method:'get',
                            url:'/api/sendInterBlog',
                            params:({    
                                title:titleToChain,
                                content:contentToChain,
                                chain:chainIdString,
                                channel:channel
                            })
                        }).then((response) =>{     
                            console.log(response);
                            if(response.data.code==200){
                                this.$confirm('博客发布成功',{
                                    confirmButtonText: '确认',
                                    type: 'success'
                                });
                                this.$axios({
                                    method:'get',
                                    url:'/api/insertInterBlog',
                                    params:({    
                                        title:titleToChain,
                                        content:content,
                                    })
                                }).then((response) =>{     
                                    if(response.data.code!=200){
                                        console.log('跨链博客发布失败：'+response.data.msg);
                                    }
                                }).catch((error) =>{
                                    console.log('跨链博客发布失败：'+error);
                                })
                            }else{
                                this.$confirm('博客发布失败：'+ response.data.data,{
                                confirmButtonText: '确认',
                                type: 'error'
                                });
                            }   
                        }).catch((error) =>{
                            this.$confirm('博客发布失败：'+error,{
                            confirmButtonText: '确认',
                            type: 'error'
                            });
                        })
                    });
                }
			});
		}
	},
	mounted(){
		this.getAddress();
	}
}
</script>
