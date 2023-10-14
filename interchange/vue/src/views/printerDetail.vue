<style scoped>
    .text{
        background-color: #e9f1f6; 
        font-size:16px; 
        padding-left:20px; 
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
			<!-- 详细信息表单 -->
            <div style="margin-top:5%;">			
				<h1>打印机详细信息</h1>
                <div class="text" style="height:40px;  line-height:40px;">打印机名称&emsp;&emsp;&emsp;&emsp;{{  name }}</div>
                <div class="text" style="height:40px;  line-height:40px;">所属公司&emsp;&emsp;&emsp;&emsp;&emsp;{{ company }}</div>
				<div class="text" style="height:220px; padding-top:10px">
					<div style="line-height:50px">品牌&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;{{ brand }}</div>
					<div style="line-height:50px">型号&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;{{ model }}</div>
					<div style="line-height:50px">打印类型&emsp;&emsp;&emsp;&emsp;&emsp;{{ type }}</div>
					<div style="line-height:50px">上市时间&emsp;&emsp;&emsp;&emsp;&emsp;{{ time }}</div>
                </div>
                <div class="text" style="height:200px; padding-top:10px">
                    <div  style="float:left; padding-top:20px">
                        <div style="line-height:50px">区块高度&emsp;&emsp;&emsp;&emsp;&emsp;{{ height }}</div>
						<div style="line-height:50px">当前区块哈希值&emsp;&emsp;{{ hash }}</div>
						<div style="line-height:50px">时间戳&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;{{ timestamp }}</div>	
                    </div>
                    <div style="float:left; margin-left:2%" v-if="true">
                        <img :src="imgSrc" width="100" style="margin-top:50%;">
                    </div>
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
	name: 'blogDetail',
	components: {
		topMenu,
		sideMenu
	},
	data(){
		return {
            name:"",
			company:"",
			brand:"",
			model:"",
			type:"",
			time:"",
			height:"",
			hash:"",
			timestamp:"",
            imgSrc: require('../images/pad.png'),
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
		// 根据id获取交易信息
		getTradeById(){
            let title = this.$route.query.title;
			this.$axios({
				method:'get',
				url:'/api/getTitleById',
				params:({    
					title: this.Trim(title)
				})
			}).then((response)=>{
				// console.log(response);
				if(response.data.code==200){
					if(response.data.data!=null){
						let responseData = response.data.data; 
						let arrTitle = responseData.title.split("@");
						let arr = responseData.content.split("@");
						//将信息推入到自己发送列表中
						this.name = arrTitle[1];
						this.company = arrTitle[0];
						this.brand = arr[0];
						this.model = arr[1];
						this.type = arr[2];
						this.time = arr[3];
						this.height = responseData.height;
						this.hash = responseData.txhash;
						this.timestamp = responseData.timestamp;
					}
				}
			});
		},
		back(){
			this.$router.back();
		}
	},
	mounted(){
        this.getTradeById();
	}
}
</script>
