<template>

  <div>

    <div class="container">
        <h2>Работа с базой данных</h2>
        <div class="form-control">
          <label for="name">name</label>
          <input type="text" id="name" v-model.trim="name">
          <label for="lastname">l</label>
          <input type="text" id="lastname" v-model.trim="lastname">
          <label for="lastname1">l1</label>
          <input type="text" id="lastname1" v-model.trim="lastname1">
          <label for="lastname2">l2</label>
          <input type="text" id="lastname2" v-model.trim="lastname2">
          <label for="lastname3">l3</label>
          <input type="text" id="lastname3" v-model.trim="lastname3">
          <label for="lastname4">l4</label>
          <input type="text" id="lastname4" v-model.trim="lastname4">
        </div>
        <br>
        <br>
        <br>

      <button class="btn primary" :disabled="name.length === 0" @click="createPerson">Создать человека</button>
    </div>

    <div>
      <button class=”Search__button” @click="loadPeople()">CALL Spring Boot REST backend service</button>
      <button @click="clear"> Clear </button>
      <vladilen-list
          :people="people"
          @remove="removePerson"
          @update="updatePerson"
          @patchupdate="patchUpdatePerson"
      > </vladilen-list>
    </div>

  </div>


</template>

<script>
    import VladilenList from "./VladilenList.vue";

    // import api from "./backend-api";
    import axios from 'axios'

    export default {
      components: {
        VladilenList
      },
      mounted() {
        this.loadPeople()
      },
      name: 'message',
      data() {
        return {
          response: [],
          name: '',
          lastname: '',
          lastname1: '',
          lastname2: '',
          lastname3: '',
          lastname4: '',
          people: [],
          alert: null,
        }
      },
      methods: {
        clear(){
          this.people = ''
        },
        async loadPeople() {
          try {
            const {data} = await axios.get('/api/vladilen')
            this.people = Object.keys(data).map(key => {
              return {
                id: key,
                ...data[key],
                // name: data[key].name,
                // lastname: data[key].lastname
              }
            })
          }catch(e){
            this.alert = {
              type: 'danger',
              title: 'Ошибка!',
              text: 'e'
            }
          }
          console.log(this.people)
        },
        async createPerson() {
          const response = await axios.post('/api/vladilen', {
            name: this.name,
            lastname: this.lastname,
            lastname1: this.lastname1,
            lastname2: this.lastname2,
            lastname3: this.lastname3,
            lastname4: this.lastname4,
          })
          console.log(response)
          this.name = ''
          this.lastname = ''
          this.lastname1 = ''
          this.lastname2 = ''
          this.lastname3 = ''
          this.lastname4 = ''
          await this.loadPeople()
        },
        async removePerson(id) {
          try {
            const name = this.people.find(person => person.id === id).name
            await axios.delete(`/api/vladilen/${id}`)
            this.people = this.people.filter(person => person.id !== id)

          } catch (e) {
            console.log(e.message)
          }
        },
        async updatePerson(id, name, lastname, lastname1, lastname2, lastname3, lastname4) {
          try {
            const response = await axios.put(`/api/vladilen/${id}`, {
              name: name,
              lastname: lastname,
              lastname1: lastname1,
              lastname2: lastname2,
              lastname3: lastname3,
              lastname4: lastname4
            })
            await this.loadPeople()
          } catch (e) {
            console.log(e.message)
          }
        },

        async patchUpdatePerson(id, lastname4) {
          try {

            const response = await axios.patch(`/api/vladilen/${id}`, {
              lastname4: lastname4
            })

            await this.loadPeople()

          } catch (e) {
            console.log(e.message)
          }
        },
      },
    }
</script>

<style>

</style>
