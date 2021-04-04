<template>
  <div class="container">
    <app-alert :alert="alert" @close="alert = null"></app-alert>

    <form class="card" @submit.prevent="createPerson">
      <h2>Работа с базой данных</h2>
        <div class="form-control">
          <label for="name">Введите имя</label>
          <input type="text" id="name" v-model.trim="name">
          <label for="lastname">Введите фамилию</label>
          <input type="text" id="lastname" v-model.trim="lastname">
        </div>
      <button class="btn primary" :disabled="name.length === 0" >Создать человека</button>
    </form>
  </div>

  <vladilen-list
       :people="people"
       @remove="removePerson"
       @update="updatePerson"
       @patchupdate="patchUpdatePerson"
  ></vladilen-list>
</template>

<script>

import VladilenList from "@/views/VladilenList";
import AppAlert from "@/views/AppAlert";
import axios from "axios";

export default {
  name: "vladilen",
  data() {
    return {
      name: '',
      lastname: '',
      people: [],
      alert: null,
    }
  },
  mounted() {
    this.loadPeople()
  },
  methods: {

    async createPerson() {
      try {
        const response = await axios.post('/api/vladilen', {
          name: this.name,
          lastname: this.lastname
        })
        console.log(response)
        this.alert = {
          type: 'primary',
          title: 'Успешно!',
          text: `Пользователь с именем "${this.name}" был создан`
        }
        this.name = ''
        this.lastname = ''
        await this.loadPeople()
      }catch (e) {
        console.log(e.message)
      }
    },

    async loadPeople() {
      try {
        const {data} = await axios.get('/api/vladilen')
        // if (!data) {
        //   throw new Error('Список людей пуст')
        // }
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
    async removePerson(id) {
      try {
        const name = this.people.find(person => person.id === id).name
        await axios.delete(`/api/vladilen/${id}`)
        this.people = this.people.filter(person => person.id !== id)
        this.alert = {
          type: 'primary',
          title: 'Успешно!',
          text: `Пользователь с ID: "${id}" был удален`
        }
        await this.loadPeople()
      } catch (e) {
        console.log(e.message)
      }
    },
    async updatePerson(id, name, lastname) {
      try {
        await axios.put(`/api/vladilen/${id}`, {
          name: name,
          lastname: lastname
        })
        this.alert = {
          type: 'primary',
          title: 'Успешно!',
          text: `Пользователь "${id}" был изменен на имя:"${name}" и фамилию:"${lastname}"`
        }
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
        this.alert = {
          type: 'primary',
          title: 'Успешно!',
          text: `Пользователь "${id}" был изменен на фамилию:"${lastname}", имя не изменилось`
        }
        await this.loadPeople()
      } catch (e) {
        console.log(e.message)
      }
    },

    // async createPerson() {
    //   const response = await fetch('/api/vladilen', {
    //     method: 'POST',
    //     headers: {
    //       'Content-Type': 'application/json'
    //     },
    //     body: JSON.stringify({
    //       name: this.name,
    //       lastname: this.lastname
    //     })
    //   })
    //   const firebaseData = await response.json()
    //   // console.log(firebaseData)
    //   this.people.push({
    //     name: this.name,
    //     lastname: this.lastname,
    //     id: firebaseData.name
    //   })
    //
    //   this.name = ''
    //   this.lastname = ''
    // },
  },

  components: {VladilenList, AppAlert}
}
</script>

<style scoped>

</style>