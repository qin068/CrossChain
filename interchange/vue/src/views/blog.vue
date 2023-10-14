<style scoped>

    .bottom {
        margin-top: 13px;
        line-height: 12px;
        display: flex;
        justify-content: space-between;
        align-items: center;
    }

    .image {
        /* width: 100%; */
        height: 300px;
    }

    .figure{
        /* width: 300px; */
        height: 500px;
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
            <el-row style="margin-top:60px">
                <el-col :span="23">
                    <h1>区块链跨链博客平台</h1>
                </el-col>
                <el-col :span="1">
                    <el-button @click="getBlog">刷新</el-button>
                </el-col>
            </el-row>

            <div style="margin-top:20px">
            <el-row :gutter="60">
                <el-col :span="12">
                    <el-card>
                        <div id="container1" class="figure"></div>
                    </el-card>
                </el-col>
                <el-col :span="12">
                    <el-card>
                        <div id="container2" class="figure"></div>
                    </el-card>
                </el-col>
            </el-row>
            </div>

            <div style="margin-top:60px">
                
                <el-row :gutter="40">
                <el-col 
                    v-for="item in blog" 
                    :key="item" 
                    :span="8" 
                >
                <el-card :body-style="{ padding: '10px' }">
                    <div style="display: flex; justify-content: center; align-items: center; padding-top: 20px;"><img
                    :src="item.img"
                    class="image"
                    referrerpolicy="no-referrer"
                    /></div>
                    <div style="padding: 14px">
                    <div style="text-align: center"><h1>{{ item.title }}</h1></div>
                    <h1 style="margin-top:20px">{{ item.content }}</h1>
                    <div class="bottom">
                        <h1>{{ item.company }}</h1>
                        <h1>{{ item.location }}</h1>
                    </div>
                    </div>
                </el-card>
                </el-col>
                </el-row>
            </div>

            
		</el-main>
	</el-container>
	</el-container>

</template>

<script>
import topMenu from '../components/top-menu.vue';
import sideMenu from '../components/side-menu.vue';
import { inject, onMounted } from 'vue';
import * as echarts from 'echarts';

export default {
	name: 'blog',
	components: {
		topMenu,
        sideMenu
	},
    setup(){
        onMounted(() => {

        
        })
        
    },
	data(){
		return {
            //背景图片控制
			background:{
				backgroundImage:'url('+require('../images/head-blue.jpg')+')',
				backgroundRepeat:'no-repeat',
				backgroundSize:'cover',
				width:'100%',
				height:'220px'
			},
            blog: [],
            companyStatistic:[],
            locationStatistic:[],
            companyA: 0,
            companyB: 0,
            companyC: 0,
            location : new Map()
		}
	},
	computed: {
        //钱包操作-获取当前区块链地址
		address() {
			return this.$store.getters['common/wallet/address']
		},
	},
	methods:{
        //去除字符串前后的空格（cosmos返回的字符串前总会多一个空格）
        Trim(str){ 
            return str.replace(/(^\s*)|(\s*$)/g, ""); 
        },
        //获取当前节点的链名
		getChainName(){
			return this.$axios({
				method:'get',
				url:'/api/selectAllAccount'
			}).then((response) =>{     
				// console.log(response);
				if(response.data.code==200){
                    let responseData = response.data.data; 
					for(let i=0; i<responseData.length; i++){
						if(this.$store.getters['common/wallet/address']==responseData[i].address){
							return responseData[i].chain;
						}
					}
				}
			}).catch((error) =>{
				console.log(error);
			})
		},
        //根据链名转化为链ID和公司名
        getChainByName(chainName){
            if(chainName=="CompanyA"){
				return [1,"CompanyA"];
			}else if(chainName=="CompanyB"){
				return [2,"CompanyB"];
			}else if(chainName=="CompanyC"){
				return [3,"CompanyC"];
			}
        },
        //获取博客信息，单链+跨链
        getBlog(){ 
            //清空“测试节点”列表中原有的内容
            this.blog = undefined;
			this.blog = new Array();
            this.location = undefined;
            this.location = new Map();
            this.companyStatistic = undefined;
            this.companyStatistic = new Array();
            this.locationStatistic = undefined;
            this.locationStatistic = new Array();

            this.getChainName().then(data=>{
                let chain = 1;
                let chainId = chain.toString();
                //单链
                this.$axios({
                    method:'get',
                    url:'/api/listBlog',
                    params:({    
                        chain: chainId
                    })
                }).then((response) =>{     
                    // console.log(response);
                    if(response.data.code==200){
                        let responseData = response.data.data; 
                        this.$data.companyA += responseData.length;
                        for (let i = 0; i < responseData.length; i++) {
                            let arrTitle = this.Trim(responseData[i].title).split("@");
                            let arrBody = this.Trim(responseData[i].body).split("@");
                            let location = arrBody[2].replace("\'", "");
                            if(arrBody[0] == "'"){
                                this.blog.push({
                                    img: require('../images/公司1.png'),
                                    company: arrTitle[0],
                                    title: arrTitle[1],
                                    content: arrBody[1],
                                    location: location
                                })
                            }else{
                                this.blog.push({
                                    img: arrBody[0],
                                    company: arrTitle[0],
                                    title: arrTitle[1],
                                    content: arrBody[1],
                                    location: location
                                })
                            }
                            if(!this.$data.location.has(location)){
                                this.$data.location.set(location,1);//如果不存在
                            }else{
                                this.$data.location.set(location,this.$data.location.get(location)+1);
                            }
                        }

                        //跨链
                        this.$axios({
                            method:'get',
                            url:'/api/listPost',
                            params:({    
                                chain: chainId
                            })
                        }).then((response) =>{     
                            console.log(response);
                            if(response.data.code==200){
                                let responseData = response.data.data; 
                                for (let i = 0; i < responseData.length; i++) {
                                    //获取数据库中存储的content值等信息
                                    let arrTitle = this.Trim(responseData[i].title).split("@");
                                    if(arrTitle[0] == "blog"){
                                        if(arrTitle[1] == "CompanyB"){
                                            this.$data.companyB += 1;
                                        }else if(arrTitle[1] == "CompanyC"){
                                            this.$data.companyC += 1;
                                        }                               
                                        this.$axios({
                                            method:'get',
                                            url:'/api/getTitleById',
                                            params:({    
                                                title: this.Trim(responseData[i].title)
                                            })
                                        }).then((response)=>{
                                            // console.log(response);
                                            if(response.data.code==200){
                                                // if(response.data.data!=null){
                                                    let responseData2 = response.data.data; 
                                                    let arrBody = responseData2.content.split("@"); 

                                                    if(!this.$data.location.has(arrBody[2])){
                                                        this.$data.location.set(arrBody[2],1);//如果不存在
                                                    }else{
                                                        this.$data.location.set(arrBody[2],this.$data.location.get(arrBody[2])+1);
                                                    }

                                                    if(i == responseData.length-1){
                                                        this.locationStatistic = undefined;
                                                        this.locationStatistic = new Array();
                                                        for(let kv of this.$data.location){
                                                            this.locationStatistic.push({
                                                                value: kv[1],
                                                                name: kv[0]
                                                            });
                                                        }

                                                        let myChart2 = echarts.init(document.getElementById("container2"));
                                                        myChart2.setOption({
                                                            tooltip: {
                                                                trigger: 'item'
                                                            },
                                                            legend: {
                                                                top: '5%',
                                                                left: 'center'
                                                            },
                                                            series: [
                                                                {
                                                                name: '各省用户发布博客数量统计',
                                                                type: 'pie',
                                                                radius: ['40%', '70%'],
                                                                avoidLabelOverlap: false,
                                                                itemStyle: {
                                                                    borderRadius: 10,
                                                                    borderColor: '#fff',
                                                                    borderWidth: 2
                                                                },
                                                                label: {
                                                                    show: false,
                                                                    position: 'center'
                                                                },
                                                                emphasis: {
                                                                    label: {
                                                                    show: true,
                                                                    fontSize: '40',
                                                                    fontWeight: 'bold'
                                                                    }
                                                                },
                                                                labelLine: {
                                                                    show: false
                                                                },
                                                                data: this.$data.locationStatistic
                                                                }
                                                            ]
                                                        });
                                                    }

                                                    if(arrBody[0] == "" && arrTitle[1] == "CompanyB"){
                                                        this.blog.push({
                                                            img: require('../images/公司2.png'),
                                                            company: arrTitle[1],
                                                            title: arrTitle[2],
                                                            content: arrBody[1],
                                                            location: arrBody[2]
                                                        })
                                                    }else if(arrBody[0] == "" && arrTitle[1] == "CompanyC"){
                                                        this.blog.push({
                                                            img: require('../images/公司3.png'),
                                                            company: arrTitle[1],
                                                            title: arrTitle[2],
                                                            content: arrBody[1],
                                                            location: arrBody[2]
                                                        })
                                                    }else{
                                                        this.blog.push({
                                                            img: arrBody[0],
                                                            company: arrTitle[1],
                                                            title: arrTitle[2],
                                                            content: arrBody[1],
                                                            location: arrBody[2]
                                                        })
                                                    }
                                                //}
                                            }
                                        });

                                    }
                                    
                                }

                                // console.log(this.$data.location);
                                for(let kv of this.$data.location){
                                    this.locationStatistic.push({
                                        value: kv[1],
                                        name: kv[0]
                                    });
                                }
                                let myChart2 = echarts.init(document.getElementById("container2"));
                                myChart2.setOption({
                                    tooltip: {
                                        trigger: 'item'
                                    },
                                    legend: {
                                        top: '5%',
                                        left: 'center'
                                    },
                                    series: [
                                        {
                                        name: '各省用户发布博客数量统计',
                                        type: 'pie',
                                        radius: ['40%', '70%'],
                                        avoidLabelOverlap: false,
                                        itemStyle: {
                                            borderRadius: 10,
                                            borderColor: '#fff',
                                            borderWidth: 2
                                        },
                                        label: {
                                            show: false,
                                            position: 'center'
                                        },
                                        emphasis: {
                                            label: {
                                            show: true,
                                            fontSize: '40',
                                            fontWeight: 'bold'
                                            }
                                        },
                                        labelLine: {
                                            show: false
                                        },
                                        data: this.$data.locationStatistic
                                        }
                                    ]
                                });
                                
                                let myChart1 = echarts.init(document.getElementById("container1"));
                                myChart1.setOption({
                                title: {
                                    text: '各区块链博客数量统计',
                                    left: 'center'
                                },
                                tooltip: {
                                    trigger: 'item'
                                },
                                legend: {
                                    orient: 'vertical',
                                    left: 'left'
                                },
                                series: [
                                    {
                                    name: '区块链名称',
                                    type: 'pie',
                                    radius: '50%',
                                    data: [
                                        { value: this.$data.companyA, name: 'companyA' },
                                        { value: this.$data.companyB, name: 'companyB' },
                                        { value: this.$data.companyC, name: 'companyC' },
                                    ],
                                    emphasis: {
                                        itemStyle: {
                                        shadowBlur: 10,
                                        shadowOffsetX: 0,
                                        shadowColor: 'rgba(0, 0, 0, 0.5)'
                                        }
                                    }
                                    }
                                ]
                                });
                                
                            }else{
                                this.$confirm('获取跨链博客内容失败  '+response.data.msg,{
                                confirmButtonText: '确认',
                                type: 'error'
                                });
                            }   
                        }).catch((error) =>{
                            this.$confirm('获取跨链博客内容失败  '+error,{
                            confirmButtonText: '确认',
                            type: 'error'
                            });
                        })
                    }else{
                        this.$confirm('获取超级链博客内容失败  '+response.data.msg,{
                        confirmButtonText: '确认',
                        type: 'error'
                        });
                    }   
                }).catch((error) =>{
                    this.$confirm('获取超级链博客内容失败  '+error,{
                    confirmButtonText: '确认',
                    type: 'error'
                    });
                })
            });
        }
	},
	mounted(){
        if(this.$store.getters['common/wallet/address'] != ""){
            this.getBlog();
        } 
	}
}
</script>