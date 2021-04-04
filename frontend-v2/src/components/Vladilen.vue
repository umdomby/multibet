<template>

  <div>

    <div class="container">
        <h2>Работа с базой данных</h2>
        <div class="form-control">
          <label for="name">name</label>
          <input type="text" id="name" v-model.trim="name">
          <label for="lastname">l</label>
          <input type="text" id="lastname" v-model.trim="lastname">
        </div>
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
            console.log(e.message)
          }
          console.log(this.people)
        },
        async createPerson() {
          const response = await axios.post('/api/vladilen', {
            name: this.name,
            lastname: this.lastname
          })
          console.log(response)
          this.name = ''
          this.lastname = ''
          await this.loadPeople()
        },
        async removePerson(id) {
          try {
            await axios.delete(`/api/vladilen/${id}`)
            this.people = this.people.filter(person => person.id !== id)
          } catch (e) {
            console.log(e.message)
          }
        },
        async updatePerson(id, name, lastname) {
          try {
            await axios.put(`/api/vladilen/${id}`, {
              name: name,
              lastname: lastname,
            })
            await this.loadPeople()
          } catch (e) {
            console.log(e.message)
          }
        },

        async patchUpdatePerson(id, lastname) {
          try {
            await axios.patch(`/api/vladilen/${id}`, {
              lastname: lastname
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
