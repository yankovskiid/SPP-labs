//подключаем ядро
import {Component} from 'angular2/core'
//создаём компонент (модуль) нашего приложения
@Component({
  selector: 'my-app',
  providers: [],
  //определяем, как будет выглядеть наш компонент (обратите внимание на кавычки! это не " ' ". С помощью них, мы можем построчно переносить наш код)
  template: `
    Привет,  {{name}}!
  `,
  directives: []
})
//и делаем наш компонент доступным для нашего приложения
export class App {
  constructor() {
    this.name = 'Angular2'
  }
}