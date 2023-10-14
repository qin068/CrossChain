<style>
.selectOperation{
	font-size: 24px;
	font-weight: bolder;
}
.title{
	font-size: 24px;
	font-weight: bolder;
}
.CreateOrder{
	background-color: white;
	box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04);
	padding-left: 2%;
	padding-right: 2%;
	padding-bottom: 0.2%;
}
.demo-form-inline{
	padding-left: 2%;
}
#sellOrder{
	padding-top: 2%;
}
</style>
<template>
	<div>
		<div class="container">

			<!-- 顶部图标和菜单 -->
			<topMenu></topMenu>

			<!-- 当前售卖订单和购买订单 -->
			<!-- 当前售卖订单 -->
			<div style="font-size:24px;font-weight:bolder;margin-left:37%;display:inline;" id="buyTable">当前请求购买代币订单</div>
			<el-link icon="el-icon-refresh-right" @click="Refresh" type="primary" style="margin-left:10px">Refresh</el-link>
			<br><br>
			<el-table
			:data="buyData"
			style="width: 100%;">
			<el-table-column
				label="订单编号"
				width="180">
				<template v-slot="scope">
				<span style="margin-left: 10px">{{ scope.row.orderId }}</span>
				</template>
			</el-table-column>
			<el-table-column
				label="原始代币名称"
				width="180">
				<template v-slot="scope">
				<span style="margin-left: 10px">{{ scope.row.sourceToken }}</span>
				</template>
			</el-table-column>
			<el-table-column
				label="原始代币数量"
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
				label="每目标代币价格"
				width="180">
				<template v-slot="scope">
				<span style="margin-left: 10px">{{ scope.row.price }}</span>
				</template>
			</el-table-column>
			</el-table>
			<br><br>


			<div class="selectOperation">
				Please select your operation &nbsp; &nbsp;
				<el-radio v-model="radio" label="sell" @change="getInput">sell</el-radio>
				<el-radio v-model="radio" label="buy" @change="getInput">buy</el-radio>
			</div>
			<br>
			
			
			<div style="float:left; width:48%;">
				<div class="title" style="text-align: center">Create an order book</div><br>
				<div class="CreateOrder">
				<br>
				<el-form ref="form" :model="createOrderPair">
					<el-form-item label="Current wallet address">
						<el-input v-model="createOrderPair.currentAddress" placeholder="Current wallet address"></el-input>
					</el-form-item>
					<el-form-item label="Target chain" style="width:100%">
						<el-select v-model="createOrderPair.targetChain" placeholder="Please select the target chain">
							<el-option :label="targetChain1" :value="targetChannel1"></el-option>
							<el-option :label="targetChain2" :value="targetChannel2"></el-option>
						</el-select>
						<el-button style="margin-left: 1%" @click="getTargetChain">Get target chain</el-button>
					</el-form-item>
				<el-form-item label="Source token">
					<el-input v-model="createOrderPair.sourceToken" placeholder="Source token"></el-input>
				</el-form-item>
				<el-form-item label="Target token">
					<el-input v-model="createOrderPair.targetToken" placeholder="Target token"></el-input>
				</el-form-item>
				<el-form-item>
					<el-button type="primary" @click="createPair" style="display:block;margin:0 auto">Create an order book</el-button>
				</el-form-item>
				</el-form>
				</div>
			</div>
		

			<div style="float:right; width:48%; text-align:center">
				<div class="title" id=sellTitle></div><br>
				<div class="CreateOrder">
					<el-form :model="createSellOrder" id="sellOrder">
					<el-form-item :label=this.$data.createOrderPair.sourceToken>
						<el-input v-model="createSellOrder.sourceNum" placeholder="Number of source token"></el-input>
					</el-form-item>
					<el-form-item :label=this.$data.createOrderPair.targetToken>
						<el-input v-model="createSellOrder.targetNum" placeholder="The price of source token"></el-input>
					</el-form-item>
					<el-form-item>
						<el-button type="primary" @click="createSell">Create an order</el-button>
					</el-form-item>
					</el-form>
				</div>
			</div>

			<div style="clear:both"></div>
		    <br><br>

		<div class="title" id="listTitle" style="display:inline"></div>
		<el-button type="primary" icon="el-icon-search" style="display:inline;margin-left:20px" @click="QueryOrder">Query</el-button>
		<br><br>

		<el-table
		:data="tableData"
		style="width: 100%">
		<el-table-column
			label="order id"
			width="180">
			<template v-slot="scope">
			<span style="margin-left: 10px">{{ scope.row.orderId }}</span>
			</template>
		</el-table-column>
		<el-table-column
			label="source token"
			width="180">
			<template v-slot="scope">
			<span style="margin-left: 10px">{{ scope.row.sourceToken }}</span>
			</template>
		</el-table-column>
		<el-table-column
			label="source token number"
			width="180">
			<template v-slot="scope">
			<span style="margin-left: 10px">{{ scope.row.sourceNum }}</span>
			</template>
		</el-table-column>
		<el-table-column
			label="target token"
			width="180">
			<template v-slot="scope">
			<span style="margin-left: 10px">{{ scope.row.targetToken }}</span>
			</template>
		</el-table-column>
		<el-table-column
			label="price"
			width="180">
			<template v-slot="scope">
			<span style="margin-left: 10px">{{ scope.row.price }}</span>
			</template>
		</el-table-column>
		<el-table-column label="operate">
			<template v-slot="scope">
			<el-button
				size="mini"
				type="danger"
				@click="handleDelete(scope.$index, scope.row)">删除</el-button>
			</template>
		</el-table-column>
		</el-table>
		<br><br>

		<div class="title" style="display:inline">Query account balances</div>
		<el-button type="primary" icon="el-icon-search" style="display:inline;margin-left:20px" @click="QueryBalance">Query</el-button>
		<br><br>

		<el-table
		:data="balanceData"
		style="width: 50%">
		<el-table-column
			label="token name"
			width="180">
			<template v-slot="scope">
			<span style="margin-left: 10px">{{ scope.row.tokenName }}</span>
			</template>
		</el-table-column>
		<el-table-column
			label="token number"
			width="180">
			<template v-slot="scope">
			<span style="margin-left: 10px">{{ scope.row.tokenNum }}</span>
			</template>
		</el-table-column>
		</el-table>
		<br><br>

		</div>
	</div>
</template>

<script>
import { computed } from "vue";
import $store from "../store/index";
import topMenu from '../components/top-menu.vue';
export default {
	name: 'Types',
	components: {
		topMenu
	},
	data() {
		return {
			radio: '',
			createOrderPair: {
				currentAddress:'',
				targetChain:'',
				sourceToken: '',
				targetToken: ''
			},
			targetChain1:"",
			targetChain2:"",
			targetChannel1:'',
			targetChannel2:'',
			createSellOrder: {
			sourceNum: '',
			targetNum: ''
			},
			tableData: [],
			balanceData: [],
			buyData: []
		}
	},
    methods: {
		createPair() {
			let chainId = this.getChainId();
			let chainIdString = chainId.toString();
			console.log("channel",this.createOrderPair.targetChain);
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
					this.$confirm('Create success',{
					confirmButtonText: 'OK',
					type: 'success'
					});
				}else{
					this.$confirm('Create failed',{
						confirmButtonText: 'OK',
						type: 'error'
					});
				}   
            }).catch((error) =>{
				this.$confirm('Create failed',{
				confirmButtonText: 'OK',
				type: 'error'
				});
            })
		},
		getInput(){
			let buyName;
			if(this.radio=="sell"){
				buyName="buy";
			}else{
				buyName="sell";
			}
			document.getElementById('sellTitle').innerHTML='Create an '+this.radio+' Order';
			document.getElementById('listTitle').innerHTML='List '+this.radio+' Order';
			document.getElementById('buyTable').innerHTML='Current '+buyName+' Order';
			this.buyData = undefined;
			this.buyData = new Array();
			let chain = this.getChainId();
			let buyChain1; let buyChain2;
			if(chain==1){
				buyChain1=2;
				buyChain2=3;
			}else if(chain==2){
				buyChain1=1;
				buyChain2=3
			}else if(chain==3){
				buyChain1=1;
				buyChain2=2;
			}
			let buyChainString1 = buyChain1.toString();
			let buyChainString2 = buyChain2.toString();
			let chainName = this.getChain();
			if(this.radio=="buy"){
				this.$axios({
					method:'get',
					url:'/api/listSellOrder',
					params:({    //这里是发送给后台的数据
						chain:buyChainString1
					})
				}).then((response) =>{  
					console.log("listSell1",response);   
					let responseData = response.data.data; 
					if(response.data.code==200){
						for (let i = 0; i < responseData.length; i++) {
							if(chainName == this.getChainFromCoin(responseData[i].targetToken)){
								this.buyData.push({
									orderId:responseData[i].orderId,
									sourceToken:responseData[i].sourceToken,
									sourceNum:responseData[i].sourceNum,
									targetToken:responseData[i].targetToken,
									price:responseData[i].price
								})
							}
						}
						this.$axios({
							method:'get',
							url:'/api/listSellOrder',
							params:({    //这里是发送给后台的数据
								chain:buyChainString2
							})
						}).then((response) =>{  
							console.log("listSell2",response);   
							let responseData = response.data.data; 
							if(response.data.code==200){
								for (let i = 0; i < responseData.length; i++) {
									if(chainName == this.getChainFromCoin(responseData[i].targetToken)){
										this.buyData.push({
											orderId:responseData[i].orderId,
											sourceToken:responseData[i].sourceToken,
											sourceNum:responseData[i].sourceNum,
											targetToken:responseData[i].targetToken,
											price:responseData[i].price
										})
									}else{
										continue;
									}
								}
							}
						})
					}else{
						this.$confirm('Query failed',{
						confirmButtonText: 'OK',
						type: 'error'
						});
					}  
				}).catch((error) =>{
					console.log(error);
					this.$confirm('Query failed',{
						confirmButtonText: 'OK',
						type: 'error'
					}); 
				})
			}else{
				this.$axios({
					method:'get',
					url:'/api/listBuyOrder',
					params:({    //这里是发送给后台的数据
						chain:buyChainString1
					})
				}).then((response) =>{   
					console.log("listBuy1",response);
					if(response.data.code==200){
						let responseData = response.data.data;
						for (let i = 0; i < responseData.length; i++) {
							if(chainName == this.getChainFromCoin(responseData[i].sourceToken)){
								this.buyData.push({
									orderId:responseData[i].orderId,
									sourceToken:responseData[i].sourceToken,
									sourceNum:responseData[i].sourceNum,
									targetToken:responseData[i].targetToken,
									price:responseData[i].price
								})
							}
							
						}
						this.$axios({
							method:'get',
							url:'/api/listBuyOrder',
							params:({    //这里是发送给后台的数据
								chain:buyChainString2
							})
						}).then((response) =>{  
							console.log("listBuy2",response);
							let responseData = response.data.data; 
							if(response.data.code==200){
								for (let i = 0; i < responseData.length; i++) {
									if(chainName == this.getChainFromCoin(responseData[i].sourceToken)){
										this.buyData.push({
											orderId:responseData[i].orderId,
											sourceToken:responseData[i].sourceToken,
											sourceNum:responseData[i].sourceNum,
											targetToken:responseData[i].targetToken,
											price:responseData[i].price
										})
									}
								}
							}
						})
					}else{
						this.$confirm('Query failed',{
						confirmButtonText: 'OK',
						type: 'error'
						});
					}   
				}).catch((error) =>{
					console.log(error);
					this.$confirm('Query failed',{
						confirmButtonText: 'OK',
						type: 'error'
					}); 
				})
			}
		},
		createSell() {
			let chain = this.getChainId();
			var chainString = chain.toString();
			if(this.radio=="sell"){
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
					console.log("sendSell",response);
					if(response.data.code==200){
						this.$confirm('Create success',{
						confirmButtonText: 'OK',
						type: 'success'
						});
					}else{
						this.$confirm('Create failed',{
						confirmButtonText: 'OK',
						type: 'error'
						});
					}    
				}).catch((error) =>{
					this.$confirm('Create failed',{
						confirmButtonText: 'OK',
						type: 'error'
					});  
				})
			}else{
				this.$axios({
					method:'get',
					url:'/api/sendBuyOrder',
					params:({    //这里是发送给后台的数据
						sourceToken:this.$data.createOrderPair.sourceToken,
						sourceNum:this.$data.createSellOrder.sourceNum,
						targetToken:this.$data.createOrderPair.targetToken,
						price:this.$data.createSellOrder.targetNum,
						chain:chainString,
						channel:this.createOrderPair.targetChain
					})
				}).then((response) =>{    
					console.log("sendBuy",response);      
					if(response.data.code==200){
						this.$confirm('Create success',{
						confirmButtonText: 'OK',
						type: 'success'
						});
					}else{
						this.$confirm('Create failed',{
						confirmButtonText: 'OK',
						type: 'error'
						});
					}   
				}).catch((error) =>{
					this.$confirm('Create failed',{
						confirmButtonText: 'OK',
						type: 'error'
					});    
				})
			}
			
		},
		QueryOrder(){
			this.tableData = undefined;
			this.tableData = new Array();
			let chain = this.getChainId();
			var chainString = chain.toString();
			if(this.radio=="sell"){
				this.$axios({
					method:'get',
					url:'/api/listSellOrder',
					params:({    //这里是发送给后台的数据
						chain:chainString
					})
				}).then((response) =>{  
					console.log("listSell",response);   
					let responseData = response.data.data; 
					if(response.data.code==200){
						if(responseData.length==0){
							this.$confirm('No data',{
								confirmButtonText: 'OK',
								type: 'message'
							});
						}
						for (let i = 0; i < responseData.length; i++) {
							this.tableData.push({
								orderId:responseData[i].orderId,
								sourceToken:responseData[i].sourceToken,
								sourceNum:responseData[i].sourceNum,
								targetToken:responseData[i].targetToken,
								price:responseData[i].price
							})
						}
					}else{
						this.$confirm('Query failed',{
						confirmButtonText: 'OK',
						type: 'error'
						});
					}  
				}).catch((error) =>{
					this.$confirm('Query failed',{
						confirmButtonText: 'OK',
						type: 'error'
					}); 
				})
			}else{
				this.$axios({
					method:'get',
					url:'/api/listBuyOrder',
					params:({    //这里是发送给后台的数据
						chain:chainString
					})
				}).then((response) =>{   
					console.log("listBuy",response);
					if(response.data.code==200){
						let responseData = response.data.data;
						if(responseData.length==0){
							this.$confirm('No data',{
								confirmButtonText: 'OK',
								type: 'message'
							});
						}
						for (let i = 0; i < responseData.length; i++) {
							this.tableData.push({
								orderId:responseData[i].orderId,
								sourceToken:responseData[i].sourceToken,
								sourceNum:responseData[i].sourceNum,
								targetToken:responseData[i].targetToken,
								price:responseData[i].price
							})
						}
					}else{
						this.$confirm('Query failed',{
						confirmButtonText: 'OK',
						type: 'error'
						});
					}   
				}).catch((error) =>{
					this.$confirm('Query failed',{
						confirmButtonText: 'OK',
						type: 'error'
					}); 
				})
			}
		},
		handleDelete(index, row) {
			let chain = this.getChainId();
			let chainString = chain.toString();

			if(this.radio=="sell"){
				let chainName = this.getChain();
				let targetChainName = this.getChainFromCoin(row.targetToken);
				console.log("targetChainName",targetChainName);
				let channel = computed(()=>{ return $store.state.channel });   
				let chnanelObj = JSON.parse(channel.value);
				let currentChannel;
				for(let i=0; i<chnanelObj.length; i++){
					if(chnanelObj[i].port == "ibcdex"){
						if(chnanelObj[i].from == chainName){
							if(chnanelObj[i].to == targetChainName){
								currentChannel = chnanelObj[i].channelId;
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
						channel:currentChannel
					})
				}).then((response) =>{  
					console.log("cancelSell",response);
					if(response.data.code==200){
						this.$confirm('Cancel success',{
						confirmButtonText: 'OK',
						type: 'success'
						});
						this.tableData.splice(index,1);
					}else{
						this.$confirm('Cancel failed',{
						confirmButtonText: 'OK',
						type: 'error'
						});
					}    
				}).catch((error) =>{
					this.$confirm('Cancel failed',{
						confirmButtonText: 'OK',
						type: 'error'
					});  
				})
			}else{
				let chainName = this.getChain();
				console.log("sourceToken",row.sourceToken);
				console.log("targetToken",row.targetToken);
				let sourceChainName = this.getChainFromCoin(row.sourceToken);
				console.log("sourceChainName",sourceChainName);
				let channel = computed(()=>{ return $store.state.channel });   
				let chnanelObj = JSON.parse(channel.value);
				let currentChannel;
				for(let i=0; i<chnanelObj.length; i++){
					
					if(chnanelObj[i].port == "ibcdex"){
						console.log("from",chnanelObj[i].from);
						if(chnanelObj[i].from == sourceChainName){
							console.log("to",chnanelObj[i].to);
							if(chnanelObj[i].to == chainName){
								console.log("channel",chnanelObj[i].channelId);
								currentChannel = chnanelObj[i].channelId;
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
						sourceToken:row.sourceToken,
						targetToken:row.targetToken,
						orderId:row.orderId,
						chain:chainString,
						channel:currentChannel
					})
				}).then((response) =>{     
					console.log("cancelBuy",response);     
					if(response.data.code==200){
						this.$confirm('Cancel success',{
						confirmButtonText: 'OK',
						type: 'success'
						});
						this.tableData.splice(index,1);
					}else{
						this.$confirm('Cancel failed',{
						confirmButtonText: 'OK',
						type: 'error'
						});
					}   
				}).catch((error) =>{
					this.$confirm('Cancel failed',{
						confirmButtonText: 'OK',
						type: 'error'
					});    
				})
			}
		},
		QueryBalance(){
			this.balanceData = undefined;
			this.balanceData = new Array();
			let chain = this.getChainId();
			let chainString = chain.toString();
			this.$axios({
				method:'get',
				url:'/api/bankBalances',
				params:({    //这里是发送给后台的数据
					address:this.createOrderPair.currentAddress,
					chain:chainString
				})
			}).then((response) =>{  
				console.log("queryBalance",response);
				if(response.data.code==200){
					let responseData = response.data.data;
					for (let i = 0; i < responseData.length; i++) {
						this.balanceData.push({
							tokenName:responseData[i].tokenName,
							tokenNum:responseData[i].tokenNum
						})
					}
				}else{
					this.$confirm('Query failed',{
					confirmButtonText: 'OK',
					type: 'error'
					});
				}    
			}).catch((error) =>{
				this.$confirm('Query failed',{
					confirmButtonText: 'OK',
					type: 'error'
				});  
			})
		},
		Refresh(){
			this.buyData = undefined;
			this.buyData = new Array();
			let chain = this.getChainId();
			let buyChain1; let buyChain2;
			if(chain==1){
				buyChain1=2;
				buyChain2=3;
			}else if(chain==2){
				buyChain1=1;
				buyChain2=3
			}else if(chain==3){
				buyChain1=1;
				buyChain2=2;
			}
			let buyChainString1 = buyChain1.toString();
			let buyChainString2 = buyChain2.toString();
			let chainName = this.getChain();
			if(this.radio=="buy"){
				this.$axios({
					method:'get',
					url:'/api/listSellOrder',
					params:({    //这里是发送给后台的数据
						chain:buyChainString1
					})
				}).then((response) =>{  
					console.log("listSell1",response);   
					let responseData = response.data.data; 
					if(response.data.code==200){
						for (let i = 0; i < responseData.length; i++) {
							console.log(responseData[i].targetToken,this.getChainFromCoin(responseData[i].targetToken));
							if(chainName == this.getChainFromCoin(responseData[i].targetToken)){
								this.buyData.push({
									orderId:responseData[i].orderId,
									sourceToken:responseData[i].sourceToken,
									sourceNum:responseData[i].sourceNum,
									targetToken:responseData[i].targetToken,
									price:responseData[i].price
								})
							}else{
								continue;
							}
						}
						this.$axios({
							method:'get',
							url:'/api/listSellOrder',
							params:({    //这里是发送给后台的数据
								chain:buyChainString2
							})
						}).then((response) =>{  
							console.log("listSell2",response);   
							let responseData = response.data.data; 
							if(response.data.code==200){
								for (let i = 0; i < responseData.length; i++) {
									if(chainName == this.getChainFromCoin(responseData[i].targetToken)){
										this.buyData.push({
											orderId:responseData[i].orderId,
											sourceToken:responseData[i].sourceToken,
											sourceNum:responseData[i].sourceNum,
											targetToken:responseData[i].targetToken,
											price:responseData[i].price
										})
									}else{
										continue;
									}
								}
							}
						})
					}else{
						this.$confirm('Query failed',{
						confirmButtonText: 'OK',
						type: 'error'
						});
					}  
				}).catch((error) =>{
					console.log(error);
					this.$confirm('Query failed',{
						confirmButtonText: 'OK',
						type: 'error'
					}); 
				})
			}else{
				this.$axios({
					method:'get',
					url:'/api/listBuyOrder',
					params:({    //这里是发送给后台的数据
						chain:buyChainString1
					})
				}).then((response) =>{   
					console.log("listBuy1",response);
					if(response.data.code==200){
						let responseData = response.data.data;
						for (let i = 0; i < responseData.length; i++) {
							this.buyData.push({
								orderId:responseData[i].orderId,
								sourceToken:responseData[i].sourceToken,
								sourceNum:responseData[i].sourceNum,
								targetToken:responseData[i].targetToken,
								price:responseData[i].price
							})
						}
						this.$axios({
							method:'get',
							url:'/api/listBuyOrder',
							params:({    //这里是发送给后台的数据
								chain:buyChainString2
							})
						}).then((response) =>{  
							console.log("listBuy2",response);   
							let responseData = response.data.data; 
							if(response.data.code==200){
								for (let i = 0; i < responseData.length; i++) {
									this.buyData.push({
										orderId:responseData[i].orderId,
										sourceToken:responseData[i].sourceToken,
										sourceNum:responseData[i].sourceNum,
										targetToken:responseData[i].targetToken,
										price:responseData[i].price
									})
								}
							}
						})
					}else{
						this.$confirm('Query failed',{
						confirmButtonText: 'OK',
						type: 'error'
						});
					}   
				}).catch((error) =>{
					this.$confirm('Query failed',{
						confirmButtonText: 'OK',
						type: 'error'
					}); 
				})
			}
		},
		initialize(){
			let chain = this.getChain();
			let channel = computed(()=>{ return $store.state.channel });   
			let chnanelObj = JSON.parse(channel.value);
			let flag = 0;
			for(let i=0; i<chnanelObj.length; i++){
				if(chnanelObj[i].port == "ibcdex"){
					if(chnanelObj[i].from == chain){
						if(flag == 0){
							this.targetChain1 = chnanelObj[i].to;
							this.targetChannel1 = chnanelObj[i].channelId;
							flag = 1;
						}else{
							this.targetChain2 = chnanelObj[i].to;
							this.targetChannel2 = chnanelObj[i].channelId;
						}
					}else{
						continue;
					}
				}else{
					continue;
				}
				
			}
		},
		getChain(){
			let mars1 = computed(()=>{ return $store.state.mars1 });
			let mars2 = computed(()=>{ return $store.state.mars2 });
			let venus1 = computed(()=>{ return $store.state.venus1 });
			let venus2 = computed(()=>{ return $store.state.venus2 });
			let earth1 = computed(()=>{ return $store.state.earth1 });
			let earth2 = computed(()=>{ return $store.state.earth2 });
			let currentAddress = this.$store.getters['common/wallet/address'];
			// console.log("currentAddress",currentAddress);
			if(currentAddress==mars1.value || currentAddress==mars2.value){
				return "mars";
			}else if(currentAddress == venus1.value || currentAddress == venus2.value){
				return "venus";
			}else if(currentAddress == earth1.value || currentAddress == earth2.value){
				return "earth";
			}
		},
		getChainId(){
			let mars1 = computed(()=>{ return $store.state.mars1 });
			let mars2 = computed(()=>{ return $store.state.mars2 });
			let venus1 = computed(()=>{ return $store.state.venus1 });
			let venus2 = computed(()=>{ return $store.state.venus2 });
			let earth1 = computed(()=>{ return $store.state.earth1 });
			let earth2 = computed(()=>{ return $store.state.earth2 });
			let currentAddress = this.createOrderPair.currentAddress
			// console.log("currentAddress",currentAddress,"earth1",earth1.value);
			if(currentAddress==mars1.value || currentAddress==mars2.value){
				return 1;
			}else if(currentAddress == venus1.value || currentAddress == venus2.value){
				return 2;
			}else if(currentAddress == earth1.value || currentAddress == earth2.value){
				return 3;
			}
		},
		getTargetChain(){
			let chain = "";
			let mars1 = computed(()=>{ return $store.state.mars1 });
			let mars2 = computed(()=>{ return $store.state.mars2 });
			let venus1 = computed(()=>{ return $store.state.venus1 });
			let venus2 = computed(()=>{ return $store.state.venus2 });
			let earth1 = computed(()=>{ return $store.state.earth1 });
			let earth2 = computed(()=>{ return $store.state.earth2 });
			let currentAddress = this.createOrderPair.currentAddress;
			// console.log("mars1",mars1.value);
			// console.log("mars2",mars2.value);
			// console.log("venus1",venus1.value);
			// console.log("venus2",venus2.value);
			// console.log("earth1",earth1.value);
			// console.log("earth2",earth2.value);
			// console.log("currentAddress",currentAddress);
			if(currentAddress == mars1.value || currentAddress == mars2.value){
				chain="mars";
			}else if(currentAddress == venus1.value || currentAddress == venus2.value){
				chain="venus";
			}else if(currentAddress == earth1.value || currentAddress == earth2.value){
				chain="earth";
			}
			// console.log("chain",chain);
			let channel = computed(()=>{ return $store.state.channel });   
			console.log("channel",channel.value);
			let chnanelObj = JSON.parse(channel.value);
			let flag = 0;
			for(let i=0; i<chnanelObj.length; i++){
				if(chnanelObj[i].port == "ibcdex"){
					if(chnanelObj[i].from == chain){
						if(flag == 0){
							this.targetChain1 = chnanelObj[i].to;
							this.targetChannel1 = chnanelObj[i].channelId;
							flag = 1;
						}else{
							this.targetChain2 = chnanelObj[i].to;
							this.targetChannel2 = chnanelObj[i].channelId;
						}
					}else{
						continue;
					}
				}else{
					continue;
				}
				
			}
			console.log("targetChain",this.targetChain1,this.targetChain2,this.targetChannel1,this.targetChannel2);
		},
		getChainFromCoin(coin){
			if(coin.search("mcx") != -1){
				return "mars";
			}else if(coin.search("vcx") != -1){
				return "venus";
			}else if(coin.search("ecx") != -1){
				return "earth";
			}
		}
    },
	mounted(){
		this.createOrderPair.currentAddress = this.$store.getters['common/wallet/address'];
		this.initialize();
	}
	
}
</script>
