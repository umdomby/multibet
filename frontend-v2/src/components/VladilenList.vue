<template>

  <div class="container">
<!--    <div v-if="people.length !== 0">-->
      <div v-for="person in people" :key="person.id">
        <h3>{{ person.id }}
        <input type="text" v-model="person.name">
        <input type="text" v-model="person.lastname">
          <input type="text" v-model="person.lastname1">
          <input type="text" v-model="person.lastname2">
          <input type="text" v-model="person.lastname3">
          <input type="text" v-model="person.lastname4">
        </h3>
        <button class="btn danger" @click="$emit('remove', person.id)">Удалить</button>
        <button class="btn danger" @click="$emit('update', person.id, person.name, person.lastname, person.lastname1, person.lastname2, person.lastname3, person.lastname4)">Сохранить всё</button>
        <button class="btn danger" @click="$emit('patchupdate', person.id, person.lastname4)">Сохранить lastname4</button>
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