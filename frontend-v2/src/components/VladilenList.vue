<template>

  <div class="container">
<!--    <div v-if="people.length !== 0">-->
      <div v-for="person in people" :key="person.id">
        <h3>{{ person.id }}
        <input type="text" v-model="person.name">
        <input type="text" v-model="person.lastname">
        </h3>
        <button class="btn danger" @click="$emit('remove', person.id)">Удалить</button>
        <button class="btn danger" @click="$emit('update', person.id, person.name, person.lastname)">Сохранить всё</button>
        <button class="btn danger" @click="$emit('patchupdate', person.id, person.lastname)">Сохранить lastname</button>
      </div>
  </div>
</template>

<script>
export default {
  name: "VladilenList",
  emits: ['remove', 'update', 'patchupdate'],
  props: ['people'],
}
</script>

<style scoped>
/*.inline {*/
/*  display: flex;*/
/*  justify-content: space-between;*/
/*  align-items: center;*/
/*}*/
</style>