const path = require('path')

module.exports = {
	lintOnSave:false,
	devServer: {
		proxy: {
            //名字可以自定义，这里我用的是api
            '/api': {
              target: 'http://127.0.0.1:8888/',//设置你调用的接口域名和端口号 别忘了加http
              changeOrigin: true,//这里设置是否跨域
              ws: true,
              pathRewrite: {
                '^/api': ''
              }
            }
        }
	},
	configureWebpack: {
		resolve: {
			symlinks: false,
			alias: {
				vue$: path.resolve('./node_modules/vue/dist/vue.esm-bundler.js')
			}
		}
	}
}
