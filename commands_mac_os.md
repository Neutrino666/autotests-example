После каждой отработки скрипта закрываем вкладку терминала 'RunMarkdown'
```sh
  bash docker-compose-e2e.sh # Запуск контейнеров Allure serve и Selenoid
```
```sh
  bash clear-docker.sh # Удаление всех контейнеров
```
```sh
  docker volume create allure-results # Создать volume для сохранения данных отчётов в контейнере Allure server
```