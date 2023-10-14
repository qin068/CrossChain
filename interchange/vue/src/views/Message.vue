<style>

.titleCenter{
	font-size: 24px;
	font-weight: bolder;
	text-align: center;
}
.background{
	background-color: white;
	box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04);
	padding-top: 5%;
	padding-left: 5%;
	padding-right: 5%;
	padding-bottom: 1%;
}
</style>
<template>
	<div>
		<div class="container">

			<div style="width: 100%;overflow: hidden">

			<div style="float:left; width:48%">
				<div class="titleCenter">Please send your message</div>
				<br>
				<div class="background">
					<el-form :model="post">
						<el-form-item label="Current wallet address">
							<el-input v-model="post.currentAddress" placeholder="Current wallet address"></el-input>
						</el-form-item>
						<el-form-item label="Target chain" style="width:100%">
							<el-select v-model="post.targetChain" placeholder="Please select the target chain">
								<el-option :label="targetChain1" :value="targetChannel1"></el-option>
								<el-option :label="targetChain2" :value="targetChannel2"></el-option>
							</el-select>
							<el-button style="margin-left: 1%" @click="getTargetChain">Get target chain</el-button>
						</el-form-item>
						<el-form-item label="Title">
							<el-input v-model="post.title" placeholder="Title"></el-input>
						</el-form-item>
						<el-form-item label="Content">
							<el-input v-model="post.content" placeholder="Content" type="textarea"></el-input>
						</el-form-item>
						<el-form-item style="width:100%">
							<el-button type="primary" @click="sendPost" style="display:block;margin:0 auto">Send message</el-button>
						</el-form-item>
					</el-form>
				</div>
			</div>

			<div style="float:right; width:48%; text-align:center">
				<div style="font-size:24px;font-weight:bolder;display:inline" id="buyTable">The message has been sent</div>
				<el-link icon="el-icon-refresh-right" @click="RefreshSend" type="primary" style="margin-left:10px">Refresh</el-link>
				<br><br>
				<el-table
				:data="sendData"
				style="width: 100%;">
				<el-table-column
					prop="id"
					label="id"
					width="180">
				</el-table-column>
				<el-table-column
					prop="title"
					label="title"
					width="180">
				</el-table-column>
				<el-table-column
					prop="creator"
					label="creator"
					width="180">
				</el-table-column>
				</el-table>
			</div>

			</div>

			<br><br>
			<div style="font-size:24px;font-weight:bolder;margin-left:37%;display:inline" id="buyTable">Received Message</div>
			<el-link icon="el-icon-refresh-right" @click="Refresh" type="primary" style="margin-left:10px">Refresh</el-link>
			<br><br>
			<el-table
			:data="receiveData"
			style="width: 100%">
			<el-table-column
				prop="id"
				label="id"
				width="180"
				align="center">
			</el-table-column>
			<el-table-column
				prop="title"
				label="title"
				width="180"
				align="center">
			</el-table-column>
			<el-table-column
				prop="content"
				label="content"
				width="180"
				align="center">
			</el-table-column>
			<el-table-column
				prop="creator"
				label="creator"
				align="center">
			</el-table-column>
			</el-table>

            <br><br><br><br>
            <!-- <div class="titleCenter">Decrypt message</div>
            <br>
            <div class="background">
                <el-form :model="message">
                <el-form-item label="Key">
                    <el-input v-model="message.key" placeholder="Please inpput your Key"></el-input>
                </el-form-item>
                <el-form-item label="Message">
                    <el-input v-model="message.message" placeholder="Please inpput the message you received"></el-input>
                </el-form-item>
                <el-form-item style="width:100%">
                    <el-button type="primary" @click="decrypt" style="display:block;margin:0 auto">Decrypt</el-button>
                </el-form-item>
                <el-form-item>
                    <el-input v-model="message.result" placeholder="The results will be displayed here" id="result" readonly></el-input>
                </el-form-item>
                
                </el-form>
            </div>

            <br><br><br><br> -->

		</div>
	</div>
</template>

<script>
import { computed } from "vue";
import $store from "../store/index";
export default {
	name: 'Types',
	data() {
		return {
			post: {
				currentAddress:'',
				targetChain:'',
				title: '',
				content: ''
			},
			targetChain1:'',
			targetChain2:'',
			targetChannel1:'',
			targetChannel2:'',
            message: {
                key: '',
                message: '',
                result: ''
			},
			sendData: [],
			receiveData: [],
            
		}
	},
	methods: {
		sendPost(){
			let chainId = this.getChainId();
			let chainIdString = chainId.toString();

			this.$axios({
                method:'get',
                url:'/api/sendPost',
                params:({    //这里是发送给后台的数据
                      title:this.$data.post.title,
                      content:this.$data.post.content,
					  chain:chainIdString,
					  channel:this.post.targetChain
                })
            }).then((response) =>{      
				if(response.data.code==200){
					this.$confirm('Send success',{
					confirmButtonText: 'OK',
					type: 'success'
					});
				}else{
					this.$confirm('Send failed',{
						confirmButtonText: 'OK',
						type: 'error'
					});
				}   
            }).catch((error) =>{
				this.$confirm('Send failed',{
				confirmButtonText: 'OK',
				type: 'error'
				});
            })
		},
		RefreshSend(){
			this.sendData = undefined;
			this.sendData = new Array();

			let chainId = this.getChainId();
			console.log(chainId);
			let chainIdString = chainId.toString();

			this.$axios({
				method:'get',
				url:'/api/listSendPost',
				params:({    //这里是发送给后台的数据
					chain:chainIdString
				})
			}).then((response) =>{     
				console.log(response); 
				if(response.data.code==200){
					let responseData = response.data.data; 
					for (let i = 0; i < responseData.length; i++) {
						this.sendData.push({
							id:responseData[i].id,
							title:responseData[i].title,
							creator:responseData[i].creator
						})
					}
				}else{
					this.$confirm('List message failed',{
						confirmButtonText: 'OK',
						type: 'error'
					});
				}   
			}).catch((error) =>{
				this.$confirm('List message failed',{
				confirmButtonText: 'OK',
				type: 'error'
				});
			})
		},
		Refresh(){
			this.receiveData = undefined;
			this.receiveData = new Array();

			let chainId = this.getChainId();
			let chainIdString = chainId.toString();

			this.$axios({
				method:'get',
				url:'/api/listPost',
				params:({    //这里是发送给后台的数据
					chain:chainIdString
				})
			}).then((response) =>{      
				console.log(response);
				if(response.data.code==200){
					let responseData = response.data.data; 
					for (let i = 0; i < responseData.length; i++) {
						this.receiveData.push({
							id:responseData[i].id,
							title:responseData[i].title,
							content:responseData[i].content,
							creator:responseData[i].creator
						})
					}
				}else{
					this.$confirm('List message failed',{
						confirmButtonText: 'OK',
						type: 'error'
					});
				}   
			}).catch((error) =>{
				this.$confirm('List message failed',{
				confirmButtonText: 'OK',
				type: 'error'
				});
			})
		},
        // decrypt(){
		// 	this.$axios({
        //         method:'get',
        //         url:'/api/decrypt',
        //         params:({    //这里是发送给后台的数据
        //               key:this.$data.message.key,
        //               content:this.$data.message.message,
        //         })
        //     }).then((response) =>{    
        //         console.log(response); 
		// 		if(response.status==200){
		// 			document.getElementById('result').value = response.data;
		// 		}else{
		// 			this.$confirm('Decrypt failed',{
		// 				confirmButtonText: 'OK',
		// 				type: 'error'
		// 			});
		// 		}   
        //     }).catch((error) =>{
		// 		this.$confirm('Decrypt failed',{
		// 		confirmButtonText: 'OK',
		// 		type: 'error'
		// 		});
        //     })
		// },
		initialize(){
			let chain = this.getChain();
			let channel = computed(()=>{ return $store.state.channel });   
			let chnanelObj = JSON.parse(channel.value);
			let flag = 0;
			for(let i=0; i<chnanelObj.length; i++){
				if(chnanelObj[i].port == "blog"){
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
			let currentAddress = this.post.currentAddress
			// console.log("currentAddress",currentAddress);
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
			let currentAddress = this.post.currentAddress;
			// console.log("earth1",earth1.value);
			// console.log("currentAddress",currentAddress);
			if(currentAddress == mars1.value || currentAddress == mars2.value){
				chain="mars";
			}else if(currentAddress == venus1.value || currentAddress == venus2.value){
				chain="venus";
			}else if(currentAddress == earth1.value || currentAddress == earth2.value){
				chain="earth";
			}
			let channel = computed(()=>{ return $store.state.channel });   
			let chnanelObj = JSON.parse(channel.value);
			let flag = 0;
			for(let i=0; i<chnanelObj.length; i++){
				// console.log(chnanelObj[i].from);
				if(chnanelObj[i].port == "blog"){
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
		}
	},
	mounted(){
		this.Refresh();
		this.post.currentAddress = this.$store.getters['common/wallet/address'];
		this.initialize();
	}
}
</script>
