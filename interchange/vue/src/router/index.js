import { createRouter, createWebHistory } from 'vue-router'
// import Index from '@/views/Index.vue'
import Types from '@/views/Types.vue'
import Message from '@/views/Message.vue'
import Single from '@/views/Single.vue'
import Relayers from '@/views/Relayers.vue'
import Admin from '@/views/admin.vue'
import Company from '@/views/company.vue'
import TradeDetail from '@/views/printerDetail.vue'
import AddNode from '@/views/addNode.vue'
import NodeDetail from '@/views/nodeDetail.vue'
import NodeList from '@/views/nodeList.vue'
import SellToken from '@/views/sellToken.vue'
import BuyToken from '@/views/buyToken.vue'
import Blog from '@/views/blog.vue'
import Printer from '@/views/printer.vue'
import PrinterDetail from '@/views/printerDetail.vue'
import AddBlog from '@/views/addBlog.vue'
import Encrypt from '@/views/encrypt.vue'


const routerHistory = createWebHistory()
const routes = [
	{
		path: '/',
		component: SellToken,
		meta: {
			keepAlive: true
	  	} 
	},
	{ 
		path: '/types', 
		component: Types,
		meta: {
			keepAlive: true
	  	} 
	},
	{ 
		path: '/message', 
		component: Message,
		meta: {
			keepAlive: true
	  	} 
	},
	{ 
		path: '/single', 
		component: Single,
		meta: {
			keepAlive: true
	  	} 
	},
	{ 
		path: '/relayers', 
		component: Relayers,
		meta: {
			keepAlive: true
	  	} 
	},
	{ 
		path: '/admin', 
	  	component: Admin,
	  	meta: {
			keepAlive: false
	  	} 
	},
	{ 
		path: '/company', 
	  	component: Company,
	  	meta: {
			keepAlive: true
	  	} 
	},
	{
		path: '/printerDetail', 
	  	component: PrinterDetail,
	  	meta: {
			keepAlive: true
	  	} 
	},
	{
		path: '/addNode', 
	  	component: AddNode,
	  	meta: {
			keepAlive: true
	  	} 
	},
	{
		path: '/nodeDetail', 
	  	component: NodeDetail,
	  	meta: {
			keepAlive: true
	  	} 
	},
	{
		path: '/nodeList', 
	  	component: NodeList,
	  	meta: {
			keepAlive: true
	  	} 
	},
	{
		path: '/sellToken', 
	  	component: SellToken,
	  	meta: {
			keepAlive: true
	  	} 
	},
	{
		path: '/buyToken', 
	  	component: BuyToken,
	  	meta: {
			keepAlive: true
	  	} 
	},
	{
		path: '/printer', 
	  	component: Printer,
	  	meta: {
			keepAlive: true
	  	} 
	},
	{
		path: '/blog', 
	  	component: Blog,
		meta: {
			keepAlive: true
	  	} 
	},
	{
		path: '/addBlog', 
	  	component: AddBlog,
	  	meta: {
			keepAlive: true
	  	} 
	},
	{
		path: '/encrypt', 
	  	component: Encrypt,
	  	meta: {
			keepAlive: true
	  	} 
	}
]

const router = createRouter({
	history: routerHistory,
	routes
})

export default router
