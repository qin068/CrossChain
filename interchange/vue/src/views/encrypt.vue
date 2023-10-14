<style scoped>
.encrypt{
    margin-top: 50px;
}
.title{
    margin-top: 20px;
    font-weight: bolder;
    text-align: center;
    font-size: 24px;
}

.encrypt-form{
    background-color: white;
    box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04);
    padding-top: 2%;
    padding-left: 2%;
    padding-right: 2%;
    padding-bottom: 0.2%;
}
.demo-form-inline{
    padding-left: 2%;
}


</style>>

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
            <div class="encrypt">
                <el-row :gutter="20">
                <el-col :span="12">
                <!-- 当前接收到的加密信息 -->
                <div class="encrypt-form">
                    <div style="font-size:24px;font-weight:bolder; margin-left: 35%; display:inline;">当前接收到的信息</div>
                    <el-link icon="el-icon-refresh-right" @click="refresh" type="primary" style="margin-left:10px">刷新</el-link>
                    <br><br>
                </div>
                <el-table
                :data="table"
                style="width: 100%;"
                max-height="250"
                :header-cell-style="{textAlign: 'center',}"
                :empty-text=this.empty>
                <el-table-column
                    label="发送方"
                    width="100">
                    <template v-slot="scope">
                    <span>{{ scope.row.source }}</span>
                    </template>
                </el-table-column>
                <el-table-column
                    label="标题"
                    width="180">
                    <template v-slot="scope">
                    <span style="margin-left: 10px">{{ scope.row.title }}</span>
                    </template>
                </el-table-column>
                <el-table-column
                    label="主要内容">
                    <template v-slot="scope">
                    <span style="margin-left: 10px">{{ scope.row.content }}</span>
                    </template>
                </el-table-column>
                </el-table>

                <!-- 解密 -->
                <div class="encrypt-form" style="margin-top: 20px;">
                <el-form ref="form" :model="decrypt">
                <div class="title">解密接收信息</div>
                <br><br>
                <el-form-item label="请选择加密算法">
                    <el-select v-model="decrypt.algorithm" placeholder="请选择所要使用的加密算法">
                        <el-option v-for="item in decryptSelect" :key="item.content" :value="item.content" :label="item.content"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="待解密内容">
                    <el-input v-model="decrypt.content" placeholder="请输入待解密内容"></el-input>
                </el-form-item>
                <el-form-item label="解密秘钥">
                    <el-input v-model="decrypt.key" placeholder="请输入解密秘钥"></el-input>
                </el-form-item>
                <el-form-item label="解密结果">
                    <el-input v-model="decrypt.result" :rows="2" type="textarea" placeholder="解密结果" readonly></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="decryptMessage" style="display:block;margin:0 auto">解密接收信息</el-button>
                </el-form-item>
                </el-form>
                </div>
                </el-col>

                <!-- 发送加密信息 -->
                <el-col :span="12">
                <div class="encrypt-form">
                <el-form ref="form" :model="encrypt">
                    <div class="title">发送加密信息</div>
                    <br><br>
                    <el-form-item label="当前账户地址">
                        <el-input v-model="encrypt.currentAddress" placeholder="请输入当前账户地址"></el-input>
                    </el-form-item>
                    <el-form-item label="目标区块链">
                        <el-select v-model="encrypt.targetChain" placeholder="请选择想要通信的目标区块链" style="float:left">
                            <el-option v-for="item in options" :key="item.targetChainName" :value="item.targetChannel" :label="item.targetChainName" @click="labelClick(item.targetChainName)"></el-option>
                        </el-select>
                        <el-button style="margin-left: 1%" @click="getTargetChain">查询可供选择的目标区块链</el-button>
                    </el-form-item>
                <el-form-item label="请选择加密算法">
                    <el-select v-model="encrypt.algorithm" placeholder="请选择所要使用的加密算法">
                        <el-option v-for="item in algorithm" :key="item.algorithm" :value="item.algorithm" :label="item.algorithm"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="加密秘钥">
                    <el-input v-model="encrypt.key" placeholder="请输入秘钥"></el-input>
                </el-form-item>
                <el-form-item label="信息标题">
                    <el-input v-model="encrypt.title" placeholder="请输入信息的标题"></el-input>
                </el-form-item>
                <el-form-item label="信息主要内容">
                    <el-input v-model="encrypt.content" :rows="2" type="textarea" placeholder="请输入信息的主要内容"></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="sendEncrypt" style="display:block;margin:0 auto">发送加密信息</el-button>
                </el-form-item>
                </el-form>
                </div>
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
    name: '',
    components: {
        topMenu,
        sideMenu
    },
    data(){
    return {
        table: [],
        targetChainId: [],//目标链的两个Id值
        encrypt: {
            currentAddress:'',
            targetChain:'',
            algorithm: '',
            key: '',
            title: '',
            content: ''
        },
        options:[],//存目标链名称和channel号,
        label:'',//存储目标链选择框中最终选择的链id,
        decryptSelect:
        [{
            content: 'DES',
        },
        {
            content: 'AES',
        },
        {
            content: 'PBE',
        }],
        algorithm: 
        [{
            algorithm: 'DES',
        },
        {
            algorithm: 'AES',
        },
        {
            algorithm: 'PBE',
        }],
        decrypt: {
            algorithm: '',
            key: '',
            content: '',
            result: '',
        },
        empty:'No Data',
        CompanyAFlag: false,
        CompanyBFlag: false
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
        this.encrypt.currentAddress = this.$store.getters['common/wallet/address'];
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
                    if(this.$store.getters['common/wallet/address']==responseData[i].address){
                        return responseData[i].chain;
                    }
                }
            }
        }).catch((error) =>{
            alert(error);
        })
    },
    //根据链名转化为链ID
    getChainId(chainName){
        if(chainName=="CompanyA"){
            return 1;
        }else if(chainName=="CompanyB"){
            return 2;
        }else if(chainName=="CompanyC"){
            return 3;
        }
    },
    getTargetChain(){
        //初始化下拉选项
        this.options = [];
        this.getChainName().then(data=>{
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
    sendEncrypt(){
        //获取所在链
        this.getChainName().then(data=>{
            //预处理数据
            let chainId= this.getChainId(data);
            let chainIdString = chainId.toString();

            //对content内容进行加密
            this.$axios({
                method:'get',
                url:'/api/encrypt',
                params:({    
                    algorithm: this.encrypt.algorithm,
                    password: this.encrypt.key,
                    content: this.encrypt.content
                })
            }).then((response) =>{     
                console.log(response);
                if(response.data.code==200){
                    // console.log(response.data.data);
                    let titleToChain = 'encrypt' + '@'  + data + '@' + this.$data.encrypt.title;
                    let encryptedContent = response.data.data;
                    let contentToChain = encryptedContent.substring(0,10);
                    this.$axios({
                        method:'get',
                        url:'/api/insertInterBlog',
                        params:({    
                            title:titleToChain,
                            content:encryptedContent,
                        })
                    }).then((response) =>{     
                        if(response.data.code!=200){
                            console.log('入库失败：'+response.data.msg);
                        }
                    }).catch((error) =>{
                        console.log('入库失败：'+error);
                    });
                    //将节点信息上链
                    this.$axios({
                        method:'get',
                        url:'/api/sendInterMessage',
                        params:({    
                            title:titleToChain,
                            content:contentToChain,
                            chain:chainIdString,
                            channel:this.encrypt.targetChain
                        })
                    }).then((response) =>{     
                        console.log(response);
                        if(response.data.code==200){
                            this.$confirm('加密消息发送成功',{
                                confirmButtonText: '确认',
                                type: 'success'
                            });
                        }else{
                            this.$confirm('加密消息发送失败：'+ response.data.data,{
                            confirmButtonText: 'OK',
                            type: 'error'
                            });
                        }   
                    }).catch((error) =>{
                        this.$confirm('加密消息发送失败：'+error,{
                        confirmButtonText: 'OK',
                        type: 'error'
                        });
                    })
                }else{
                    this.$confirm('加密失败：'+ response.data.msg,{
                    confirmButtonText: '确认',
                    type: 'error'
                    });
                }   
            }).catch((error) =>{
                this.$confirm('加密失败：'+error,{
                confirmButtonText: '确认',
                type: 'error'
                });
            })
        });
    },
    //获取博客信息，单链+跨链
    refresh(){ 
        //清空“测试节点”列表中原有的内容
        this.table = undefined;
        this.table = new Array();

        this.getChainName().then(data=>{
        let chain = this.getChainId(data);
        let chainId = chain.toString();
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
                    if(arrTitle[0] == "encrypt"){                           
                        this.$axios({
                            method:'get',
                            url:'/api/getTitleById',
                            params:({    
                                title: this.Trim(responseData[i].title)
                            })
                        }).then((response)=>{
                            // console.log(response);
                            if(response.data.code==200){
                                let responseData2 = response.data.data; 
                                let arrBody = responseData2.content; 
                                this.table.push({
                                    source: arrTitle[1],
                                    title: arrTitle[2],
                                    content: arrBody
                                })
                            }
                        });

                    }
                    
                }
                
            }else{
                this.$confirm('获取密态信息失败  '+response.data.msg,{
                confirmButtonText: '确认',
                type: 'error'
                });
            }   
        }).catch((error) =>{
            this.$confirm('获取密态信息失败  '+error,{
            confirmButtonText: '确认',
            type: 'error'
            });
        })
        });
    },
    decryptMessage(){
        this.$axios({
                method:'get',
                url:'/api/decrypt',
                params:({    
                    algorithm: this.decrypt.algorithm,
                    password: this.decrypt.key,
                    content: this.decrypt.content
                })
            }).then((response) =>{     
                console.log(response);
                if(response.data.code==200){
                    // console.log(response.data.data);
                    this.$data.decrypt.result = response.data.data;
                }else{
                    this.$confirm('解密失败：'+ response.data.msg,{
                    confirmButtonText: '确认',
                    type: 'error'
                    });
                }   
            }).catch((error) =>{
                this.$confirm('解密失败：'+error,{
                confirmButtonText: '确认',
                type: 'error'
                });
            })
    }
    },
mounted(){
    if(this.$store.getters['common/wallet/address'] != ""){
        this.getAddress();
    } 
}
}
</script>