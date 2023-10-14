<style scoped>
.note-list{    
  display: flex;
  flex-wrap: wrap;
  align-items: flex-start;
  justify-content: flex-start;
}

.note-list .note-preview-item{
  width:calc(50% - 16px);
  border: 1px solid lightgray;
  border-radius: 8px;
  height: 240px;
  margin: 8px;
  padding: 4px;
}


</style>
<template>
	<div>
		<div class="container">
            <div class="note-list">
                <div class="note-preview-item"></div>
            </div>
			<!-- this line is used by starport scaffolding # 4 -->
		<!-- <SpType modulePath="user.interchange.blog" moduleType="SentPost"  /> -->
		<!-- <SpType modulePath="user.interchange.blog" moduleType="TimedoutPost"  /> -->
		<!-- <SpType modulePath="user.interchange.blog" moduleType="Message"  /> -->

		<!-- <SpType modulePath="user.interchange.message" moduleType="Message"  /> -->
		<!-- <button @click="get">点击</button> -->
        
		</div>
	</div>
</template>

<script>
export default {
	name: 'Single',
	methods: {
		get(){
			console.log(this.$store.getters);
			
		}
	}
}
</script>
