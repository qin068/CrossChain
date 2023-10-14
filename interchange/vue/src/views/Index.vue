<style scoped>

	.company{
		margin-top: 2%;
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
			<el-main>
				<div class="company">
				<img src="../images/公司1.png" @click="toCompany">
				<img src="../images/公司2.png" style="margin-left:2%" @click="toCompany">
				<img src="../images/公司3.png" style="margin-left:2%" @click="toCompany">
				</div>
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
	name: 'Index',
	components: {
		topMenu,
		sideMenu
	},
	data(){
		return {
			background:{
				backgroundImage:'url('+require('../images/head-blue.jpg')+')',
				backgroundRepeat:'no-repeat',
				backgroundSize:'cover',
				width:'100%',
				height:'400px'
			},
			nodeNumber:'',
			blockHeight:''
		}
	},
	computed: {
		address() {
			return this.$store.getters['common/wallet/address']
		}
	},
	methods:{
		// 获取系统总运行指标
		getCompanyTotal(){
			this.$axios({
				method:'get',
				url:'/api/selectAllCompanyTotal',
			}).then((response) =>{     
				console.log(response);
				if(response.data.code==200){
				  	let responseData = response.data.data[0]; 
					this.nodeNumber = responseData.tt_count;
					this.blockHeight = responseData.tt_height;
				}else{
				  this.$confirm('获取系统数据失败',{
				    confirmButtonText: 'OK',
				    type: 'error'
				  });
				}   
			}).catch((error) =>{
				this.$confirm('获取系统数据失败',{
				confirmButtonText: 'OK',
				type: 'error'
				});
			})
		},
		toCompany(){
			this.$router.push({
                path: "/company",
            });
		}
	},
	mounted(){
		this.getCompanyTotal();
	}
}
</script>
