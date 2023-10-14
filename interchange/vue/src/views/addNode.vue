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
			<!-- 系统名称 -->
			<div :style="background" style="padding-top:15%; margin-top: 2%;">
				<!-- 产品信息录入系统 -->
				<div class="addNode">
					<h1>打印机信息录入系统</h1>
					<el-form ref="form" :model="printer" label-width="100px" style="margin-top:10%">
						<el-form-item label="链上地址">
							<el-input v-model="printer.address"></el-input>
						</el-form-item>
						<el-form-item label="打印机名称">
							<el-input v-model="printer.name"></el-input>
						</el-form-item>
						<el-form-item label="所属公司">
							<el-input v-model="printer.company" disabled></el-input>
						</el-form-item>
						<el-form-item label="品牌">
							<el-input v-model="printer.brand"></el-input>
						</el-form-item>
						<el-form-item label="型号">
							<el-input v-model="printer.model"></el-input>
						</el-form-item>
						<el-form-item label="打印类型">
							<el-input v-model="printer.type"></el-input>
						</el-form-item>
						<el-form-item label="上市时间">
							<el-input v-model="printer.time"></el-input>
						</el-form-item>
						<el-form-item label="通信链">
							<el-select v-model="printer.targetChain" placeholder="请选择想要通信的目标链" style="float:left">
								<el-option v-for="item in options" :key="item.targetChainName" :value="item.targetChannel" :label="item.targetChainName" @click="labelClick(item.targetChainName)"></el-option>
							</el-select>
							<el-button style="margin-left: 1%; float:left" @click="getTargetChain">获取通信目标链</el-button>
						</el-form-item>
						<el-button type="primary" @click="addPrinter" style="width:150px">提交录入申请</el-button>
					</el-form>
				</div>
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
			background:{
				backgroundImage:'url('+require('../images/apply.jpg')+')',
				backgroundRepeat:'no-repeat',
				backgroundSize:'cover',
				width:'100%',
				height:'200px'
			},
			printer: {
				address: '',
				name: '',
				company: '',
				brand:'',
				model:'',
				type:'',
				time:'',
				targetChain:''
			},
			options:[],
			label:''
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
		//获取el-select中的label内容
		labelClick(data){
			this.label=data;
		},
		//获取当前区块链地址
		getAddress(){
			this.printer.address = this.$store.getters['common/wallet/address'];
			this.getChainName().then(data=>{
				this.printer.company = data;
			});
		},
		//由原区块链名称获取用户看到的Company名称
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
						if(this.printer.address==responseData[i].address){
							return responseData[i].chain;
						}
					}
				}
			}).catch((error) =>{
				alert(error);
			})
		},
		// 获取通信目标链,先筛选出port是blog的channel，然后看from是否是本链，是则放入to和channelId
		getTargetChain(){
			//初始化下拉选项
			this.options = [];
			this.getChainName().then(data=>{
				// console.log(this.$data.printer.company);
				if(this.$data.printer.company == "" || this.$data.printer.company == undefined){
					this.$data.printer.company = data;
				}
				this.$axios({
					method:'get',
					url:'/api/selectAllChannel'
				}).then((response) =>{     
					// console.log(response);
					if(response.data.code==200){
						let responseData = response.data.data; 
						for(let i=0; i<responseData.length; i++){
							//如果连接功能是blog
							if(responseData[i].port == "blog"){
								//如果发送链是本链
								if(responseData[i].from == data){
									//获取接收链
									let chainToCompany = responseData[i].to;
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
		// 新增节点信息
		addPrinter(){
			//获取所在链ID
			this.getChainName().then(data=>{
				let chainId= this.getChainId(data);
				let chainIdString = chainId.toString();
				//拼接传入的title内容 当前公司名+打印机名称+目标公司名
				let titleToChain = this.$data.printer.company + '@' + this.$data.printer.name + '@' + this.label;
				//拼接传入的content内容 品牌+型号+打印类型+上市时间
				let content = this.$data.printer.brand + "@" + this.$data.printer.model + "@" + this.$data.printer.type
				              + "@" + this.$data.printer.time;
				
				//将节点信息上链
				this.$axios({
					method:'get',
					url:'/api/sendPost',
					params:({    
						title:titleToChain,
						content:content,
						chain:chainIdString,
						channel:this.printer.targetChain
					})
				}).then((response) =>{     
					// console.log(response);
					if(response.data.code==200){
						this.$confirm('打印机信息录入成功',{
							confirmButtonText: '确认',
							type: 'success'
						});
					}else{
						this.$confirm('打印机信息录入失败：'+ response.data.data,{
						confirmButtonText: 'OK',
						type: 'error'
						});
					}   
				}).catch((error) =>{
					this.$confirm('产品信息录入失败：'+error,{
					confirmButtonText: 'OK',
					type: 'error'
					});
				})
			});
		}
	},
	mounted(){
		this.getAddress();
		this.getTargetChain();
	}
}
</script>
