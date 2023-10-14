<template>
	<div>
		<div v-if="$route.meta.keepAlive">
		<SpWallet ref="wallet" v-on:dropdown-opened="$refs.menu.closeDropdown()" />
		</div>
		<SpLayout>
			<template v-slot:content>
				<router-view />
			</template>
		</SpLayout>
	</div> 
</template>

<style>
body {
	margin: 0;
}
</style>

<script>
import './scss/app.scss'
import '@starport/vue/lib/starport-vue.css'
import * as echarts from 'echarts'
import { provide } from 'vue'


export default {
	name: 'App',
	setup(){
		provide('ec',echarts);
	},
	computed: {
		hasWallet() {
			return this.$store.hasModule(['common', 'wallet'])
		}
	},
	async created() {
		await this.$store.dispatch('common/env/init')
		// this.initialized = true
	},
	errorCaptured(err) {
		console.log(err)
		return false
	}
}
</script>
