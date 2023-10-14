<template> 
    <el-menu
        background-color="#000000"
        class="el-menu-vertical-demo"
        text-color="#fff"
        style="height:1000px;"        
    >
    <el-menu-item-group title="代币交换">
        <el-menu-item index="1" @click="linkToSellToken">
            <el-icon :size="20"><Coin /></el-icon>
            <span style="font-size:20px; margin-left: 10px;">售卖代币</span>
        </el-menu-item>
        <el-menu-item index="2" @click="linkToBuyToken">
            <el-icon :size="20"><ShoppingCart /></el-icon>
            <span style="font-size:20px; margin-left: 10px;">购买代币</span>
        </el-menu-item>
    </el-menu-item-group>

    <el-menu-item-group title="物联网-打印机">
        <el-menu-item index="3" @click="linkToPrinter">
        <el-icon :size="20"><Odometer /></el-icon>
        <span style="font-size:20px; margin-left: 10px;">打印机</span>
        </el-menu-item>
        <el-menu-item index="4" @click="linkToAddNode">
        <el-icon :size="20"><Edit /></el-icon>
        <span style="font-size:20px; margin-left: 10px;">打印机录入</span>
        </el-menu-item>
    </el-menu-item-group>
        
    <el-menu-item-group title="跨链博客">
        <el-menu-item index="5" @click="linkToBlog">
        <el-icon :size="20"><ChatLineRound /></el-icon>
        <span style="font-size:20px; margin-left: 10px;">博客</span>
        </el-menu-item>
        <el-menu-item index="6" @click="linkToAddBlog">
        <el-icon :size="20"><EditPen /></el-icon>
        <span style="font-size:20px; margin-left: 10px;">发布博客</span>
        </el-menu-item>
    </el-menu-item-group>

    <el-menu-item-group title="密态传递">
        <el-menu-item index="7" @click="linkToEncrypt">
        <el-icon :size="20"><Lock /></el-icon>
        <span style="font-size:20px; margin-left: 10px;">隐私信息传输</span>
        </el-menu-item>
    </el-menu-item-group>
       

    </el-menu>
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
		linkToSellToken(){
			this.$router.push({path: '/sellToken'});
		},
        linkToBuyToken(){
			this.$router.push({path: '/buyToken'});
		},
        linkToBlog(){
			this.$router.push({path: '/blog'});
		},
        linkToPrinter(){
            this.$router.push({path: '/printer'});
        },
        linkToAddBlog(){
            this.$router.push({path: '/addBlog'});
        },
        linkToEncrypt(){
            this.$router.push({path: '/encrypt'});
        }
	}
}	
</script>

