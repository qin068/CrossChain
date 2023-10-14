<style>
	
	/* 顶部菜单,存放系统名称,跳转按钮 */
	.header1 {
		display: flex;
		justify-content: space-around;
		align-items: center;
		width: 100%;
		height: 100px;
		color: white;
		font-size: 32px;
	}

</style>

<template>
	<!-- 顶部菜单栏 -->
	<div direction="vertical">
		<!-- 背景图片 -->
		<div :style="background">
			<div class="header1">
				<!-- 左侧系统名称 -->
				<h5 style="float:left">跨链信息互联互通平台</h5>	
			</div>
		</div>
		<!-- <el-menu :default-active="activeIndex" 
		class="el-menu-demo" 
		mode="horizontal"
		background-color="#000000"
		text-color="#fff"
		active-text-color="#ffd04b"
		>
			<el-menu-item index="1" @click="linkToIndex">首页</el-menu-item>
			<el-menu-item index="2" @click="linkToAddNode">信息录入</el-menu-item>
			<el-sub-menu index="2">
			<template #title>交易</template>
				<el-menu-item index="2-1" @click="linkToTransaction">代币交换</el-menu-item>
				<el-menu-item index="2-2" @click="linkToTransaction">打印机售卖</el-menu-item>
				<el-menu-item index="2-3" @click="linkToTransaction">打印机购买</el-menu-item>
			</el-sub-menu>
		</el-menu>	 -->
	</div>
    
	
</template>

<script>
	
export default {
	
	data:function(){
		return {
			// 背景
			background:{
				backgroundImage:'url('+require('../images/head-blue.jpg')+')',
				backgroundRepeat:'no-repeat',
				backgroundSize:'cover',
				width:'100%',
				height:'100px'
			},
		}
	},
	methods:{
		//按钮“首页”：跳转到/页面
		linkToIndex() {
			this.$router.push({path: '/'});
		},
		//按钮“录入”，跳转到/addNode页面
		linkToAddNode(){
			this.$router.push({path: '/addNode'});
		},
		//按钮“交易”，跳转到/types页面
		linkToTransaction(){
			this.$router.push({path: '/types'});
		}
	}
}	
</script>

