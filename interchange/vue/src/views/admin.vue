<style>
.titleLeft{
	font-size: 24px;
	font-weight: bolder;
	text-align: left;
}
.startBlockchain{
	background-color: white;
	box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04);
	padding: 30px;
	margin-bottom: 20px;
	height: 300px;
}

.connectBlockchain{
	background-color: white;
	box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04);
	padding: 30px;
	height: 80px;
	/* margin-left: 2%; */
}

.sp-fill{
	padding-top: 0%;
}

</style>

<template>
	<div>
		<div class="container">
			<div :style="background" style="color:white; text-align:center; font-size:36px; font-weight:bolder; padding-top:80px">
				跨链信息互联互通平台
			</div>
			<div class="startBlockchain">
				<div class="titleLeft">启动区块链</div>
				<br><br>
				<el-button :type="MarsColour" @click="startMars" style="float: left; width:36%;">CompanyA</el-button>
				<el-input v-model="mars1Mnemonic" placeholder="节点助记词" style="float: left; width:60%; margin-left:2%" readonly></el-input>
				<!-- <el-input v-model="mars2Mnemonic" placeholder="节点助记词" style="float: left; width:40%; margin-left:2%" readonly></el-input> -->
				<br><br>
				<el-button :type="VenusColour" @click="startVenus" style="float: left; width:36%; margin-top:1%">CompanyB</el-button>
				<el-input v-model="venus1Mnemonic" placeholder="节点助记词" style="float: left; width:60%; margin-left:2%; margin-top:1%" readonly></el-input>
				<!-- <el-input v-model="venus2Mnemonic" placeholder="节点助记词" style="float: left; width:40%; margin-left:2%; margin-top:1%" readonly></el-input> -->
				<br><br>
				<el-button :type="EarthColour" @click="startEarth" style="float: left; width:36%; margin-top:1%">CompanyC</el-button>
				<el-input v-model="earth1Mnemonic" placeholder="节点助记词" style="float: left; width:60%; margin-left:2%; margin-top:1%" readonly></el-input>
				<!-- <el-input v-model="earth2Mnemonic" placeholder="节点助记词" style="float: left; width:40%; margin-left:2%; margin-top:1%" readonly></el-input> -->
				<!-- <el-button :type="AdminColour" @click="startAdmin" style="float: left; width:16%; margin-top:1%">激励中心</el-button> -->
			</div>
			<br><br>
			<div class="connectBlockchain">
				<div class="titleLeft" style="float:left; width: 40%">连接已启动的各个链</div>
				<el-button :type="ConnectColour" @click="connect" style="float:left; width:60%">连接</el-button>
			</div>
			
		</div>

	</div>
</template>

<script>
import { computed } from "vue";
// import $store from "../store/index";
export default {
	name: 'Admin',
	data() {
        return {
			background:{
				backgroundImage:'url('+require('../images/head-blue.jpg')+')',
				backgroundRepeat:'no-repeat',
				backgroundSize:'cover',
				width:'100%',
				height:'200px'
			},
            MarsColour:"",
            VenusColour:"",
            EarthColour:"",
			// AdminColour:"",
			ConnectColour:"",
			mars1Mnemonic:"",
			// mars2Mnemonic:"",
			venus1Mnemonic:"",
			// venus2Mnemonic:"",
			earth1Mnemonic:"",
			// earth2Mnemonic:""
        }
    },
	computed: {
		address() {
			return this.$store.getters['common/wallet/address']
		}
	},
	methods:{
		//启动Mars链
		startMars(){
			this.$axios({
				method:'get',
				url:'/api/startServe',
				params:({    //这里是发送给后台的数据
				chain:"CompanyA"
				})
			}).then((response) =>{     
				if(response.data.code==200){
				  	let responseData = response.data.data; 
					this.MarsColour = "success";
					let words1 = ""; //let words2 = "";
					for(let i=0; i<responseData[0].words.length; i++){
						words1 += responseData[0].words[i];
						if(i<responseData[0].words.length-1){
							words1 += " ";
						}
					}
					// for(let i=0; i<responseData[1].words.length; i++){
					// 	words2 += responseData[1].words[i];
					// 	if(i<responseData[1].words.length-1){
					// 		words2 += " ";
					// 	}
					// }
					this.mars1Mnemonic = words1;
					// this.mars2Mnemonic = words2;
				}else{
				  this.$confirm('启动CompanyA链失败  '+response.data.msg,{
						confirmButtonText: 'OK',
						type: 'error'
				  });
				}   
			}).catch((error) =>{
				this.$confirm('启动CompanyA链失败  '+error,{
					confirmButtonText: 'OK',
					type: 'error'
				});
			})
		},
		//启动Venus链
		startVenus(){
			this.$axios({
				method:'get',
				url:'/api/startServe',
				params:({    //这里是发送给后台的数据
				chain:"CompanyB"
				})
			}).then((response) =>{     
				if(response.data.code==200){
				  	let responseData = response.data.data; 
					this.VenusColour = "success";
					let words1 = ""; //let words2 = "";
					for(let i=0; i<responseData[0].words.length; i++){
						words1 += responseData[0].words[i];
						if(i<responseData[0].words.length-1){
							words1 += " ";
						}
					}
					// for(let i=0; i<responseData[1].words.length; i++){
					// 	words2 += responseData[1].words[i];
					// 	if(i<responseData[1].words.length-1){
					// 		words2 += " ";
					// 	}
					// }
					this.venus1Mnemonic = words1;
					// this.venus2Mnemonic = words2;
				}else{
				  	this.$confirm('启动CompanyB链失败  '+response.data.msg,{
				   	 	confirmButtonText: 'OK',
				    	type: 'error'
				  	});
				}   
			}).catch((error) =>{
				this.$confirm('启动CompanyB链失败  '+error,{
				confirmButtonText: 'OK',
				type: 'error'
				});
			})
		},
		//启动Earth链
		startEarth(){	
			this.$axios({
				method:'get',
				url:'/api/startServe',
				params:({    //这里是发送给后台的数据
				chain:"CompanyC"
				})
			}).then((response) =>{     
				if(response.data.code==200){
				  	let responseData = response.data.data; 
					this.EarthColour = "success";
					let words1 = ""; //let words2 = "";
					for(let i=0; i<responseData[0].words.length; i++){
						words1 += responseData[0].words[i];
						if(i<responseData[0].words.length-1){
							words1 += " ";
						}
					}
					// for(let i=0; i<responseData[1].words.length; i++){
					// 	words2 += responseData[1].words[i];
					// 	if(i<responseData[1].words.length-1){
					// 		words2 += " ";
					// 	}
					// }
					this.earth1Mnemonic = words1;
					// this.earth2Mnemonic = words2;
				}else{
				  this.$confirm('启动CompanyC链失败  '+response.data.msg,{
				    confirmButtonText: 'OK',
				    type: 'error'
				  });
				}   
			}).catch((error) =>{
				this.$confirm('启动CompanyC链失败  '+error,{
				confirmButtonText: 'OK',
				type: 'error'
				});
			})
		},
		// startAdmin(){	
		// 	this.$axios({
		// 		method:'get',
		// 		url:'/api/startServe',
		// 		params:({    //这里是发送给后台的数据
		// 		chain:"admin"
		// 		})
		// 	}).then((response) =>{     
		// 		if(response.data.code==200){
		// 		  	let responseData = response.data.data; 
		// 			this.AdminColour = "success";
		// 		}else{
		// 		  this.$confirm('启动激励中心链失败：'+response.data.msg,{
		// 		    confirmButtonText: 'OK',
		// 		    type: 'error'
		// 		  });
		// 		}   
		// 	}).catch((error) =>{
		// 		this.$confirm('启动激励中心链失败：'+error,{
		// 		confirmButtonText: 'OK',
		// 		type: 'error'
		// 		});
		// 	})
		// },
		//连接三个链
		connect(){
			this.$axios({
				method:'get',
				url:'/api/getAllChannel',
			}).then((response) =>{   
				this.ConnectColour = "success";
            })
		}
	}
}
</script>