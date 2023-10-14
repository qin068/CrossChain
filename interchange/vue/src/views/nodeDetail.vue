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
    }

    .trade{
        margin-top: 5%;
    }

    .text{
        background-color: #e9f1f6; 
        font-size:16px; 
        padding-left:20px; 
        margin-top:2%
    }

</style>
<template>
	<div>
		<div class="container">
			<!-- 顶部图标和菜单 -->
			<topMenu></topMenu>

			<!-- 系统名称 -->
			<div :style="background">
				<div class="top">Tses区块链系统互联互通平台</div>
			</div>

			<!-- 详细信息表单,数据从链及数据库中获取，由于数据条目不固定，故采取HTMl嵌入的形式 -->
			<div v-html="searchHTML" style="margin-top:5%;"></div>
            
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
			searchHTML:"",
		}
	},
	computed: {
		address() {
			return this.$store.getters['common/wallet/address']
		}
	},
	methods:{
		getNode(){
			//接收传递过来的数组
			let search = JSON.parse(decodeURIComponent(this.$route.query.search));
			// console.log(search);
			//对于传递过来的数据，搜索数据库中存储的匹配的hash值等信息
			for(let i=0; i<search.length; i++){
				this.$axios({
					method:'get',
					url:'/api/getTitleById',
					params:({    
						title: search[i].title
					})
				}).then((response) =>{     
					if(response.data.code==200){
						let responseData = response.data.data; 
						if(responseData!=null){
							let arr = search[i].content.split("@");
							let arrTitle = search[i].title.split("@");
							let state = "";
							//由于节点数据验证成功和失败时，文字颜色不一致，图片是否显示不一致，暂尝试的动态写入方法均失败，不知如何动态写入，只能暂时采用这种静态区分的形式
							if(arr[2]=="验证通过"){
								state = "节点数据验证成功";
								this.searchHTML += "<div style='margin-top:5%'><h1>您所查询的节点信息</h1><div class='text' style='height:40px;line-height:40px;background-color:#e9f1f6;font-size:16px; padding-left:20px; margin-top:2%'>节点ID&emsp;&emsp;&emsp;&emsp;&emsp;"+
								arr[0]+"</div><div style='height:40px; line-height:40px; background-color: #e9f1f6; font-size:16px; padding-left:20px; margin-top:2%'>交易哈希&emsp;&emsp;&emsp;&emsp;"+
								responseData.txhash+"</div><div style='height:200px; padding-top:10px; background-color: #e9f1f6; font-size:16px; padding-left:20px; margin-top:2%'><div style='padding-top:20px'><div style='line-height:50px'>节点所在区块链&emsp;"+
								arrTitle[0]+"</div><div style='line-height:50px'>节点名称&emsp;&emsp;&emsp;&emsp;"+
								arrTitle[1]+"</div><div style='line-height:50px'>节点位置&emsp;&emsp;&emsp;&emsp;"+
								arr[1]+"</div></div></div><div style='height:200px; padding-top:10px; background-color: #e9f1f6; font-size:16px; padding-left:20px; margin-top:2%'><div  style='float:left; padding-top:20px'><div style='line-height:50px;'><div style='float:left;'>节点状态&emsp;&emsp;&emsp;&emsp;</div><div style='float:left; color:green;'>"+
								state+"</div></div><div style='line-height:50px'>时间戳&emsp;&emsp;&emsp;&emsp;&emsp;"
								+responseData.timestamp+"</div><div style='line-height:50px'>区块高度&emsp;&emsp;&emsp;&emsp;"
								+responseData.height+"</div></div><div style='float:left; margin-left:2%'><img src='/pad.png' width='100' style='margin-top:50%;'></div></div></div>"
							}else{
								state = "节点数据验证失败";
								this.searchHTML += "<div style='margin-top:5%'><h1>您所查询的节点信息</h1><div class='text' style='height:40px;line-height:40px;background-color:#e9f1f6;font-size:16px; padding-left:20px; margin-top:2%'>节点ID&emsp;&emsp;&emsp;&emsp;&emsp;"+
								arr[0]+"</div><div style='height:40px; line-height:40px; background-color: #e9f1f6; font-size:16px; padding-left:20px; margin-top:2%'>交易哈希&emsp;&emsp;&emsp;&emsp;"+
								responseData.txhash+"</div><div style='height:200px; padding-top:10px; background-color: #e9f1f6; font-size:16px; padding-left:20px; margin-top:2%'><div style='padding-top:20px'><div style='line-height:50px'>节点所在区块链&emsp;"+
								arrTitle[0]+"</div><div style='line-height:50px'>节点名称&emsp;&emsp;&emsp;&emsp;"+
								arrTitle[1]+"</div><div style='line-height:50px'>节点位置&emsp;&emsp;&emsp;&emsp;"+
								arr[1]+"</div></div></div><div style='height:200px; padding-top:10px; background-color: #e9f1f6; font-size:16px; padding-left:20px; margin-top:2%'><div  style='float:left; padding-top:20px'><div style='line-height:50px;'><div style='float:left;'>节点状态&emsp;&emsp;&emsp;&emsp;</div><div style='float:left; color:red;'>"+
								state+"</div></div><div style='line-height:50px'>时间戳&emsp;&emsp;&emsp;&emsp;&emsp;"
								+responseData.timestamp+"</div><div style='line-height:50px'>区块高度&emsp;&emsp;&emsp;&emsp;"
								+responseData.height+"</div></div></div></div>"
							}
							
						}
					}else{
						this.$confirm('获取交易数据过程失败  '+response.data.msg,{
							confirmButtonText: 'OK',
							type: 'error'
						});
					}   
				}).catch((error) =>{
					this.$confirm('获取交易数据过程失败  '+error,{
						confirmButtonText: 'OK',
						type: 'error'
					});
				})
			}
			
		}
	},
	mounted(){
        this.getNode();
	}
}
</script>
