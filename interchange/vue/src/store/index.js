import { createStore } from 'vuex'
import init from './config'

const store = createStore({
	state() {
		return {
			menuIndex:1
		}
	},
	mutations: {
		setMenuIndex(state,content){
			state.menuIndex=content;
		}
	}
})
init(store)
export default store
