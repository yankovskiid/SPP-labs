<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Angular2</title>
  <!--Все файлы ниже,  необходимы для работы Angular 2-->
  <script src="https://code.angularjs.org/2.0.0-beta.0/angular2-polyfills.js"></script>
  <script src="https://code.angularjs.org/tools/system.js"></script>
  <script src="https://code.angularjs.org/tools/typescript.js"></script>
  <script src="/resources/config.js"></script>
  <script src="https://code.angularjs.org/2.0.0-beta.0/Rx.js"></script>
  <script src="https://code.angularjs.org/2.0.0-beta.0/angular2.min.js"></script>
  <script src="https://code.angularjs.org/2.0.0-beta.0/http.min.js"></script>
  <script>
    System.import('app')
            .catch(console.error.bind(console));
  </script>
</head>

<body>
<my-app>
  загрузка...
</my-app>
</body>
</html>
