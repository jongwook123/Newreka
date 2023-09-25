# 실행 명령어

## Elasticsearch & Kibana

1. `sudo docker build -t my-elasticsearch .`
2. `sudo docker-compose up -d`

## Filebeat

`sudo nohup ./filebeat -c newreka.yml -e`

## Kafka & Kafka ui

`sudo docker-compose up -d`

## Crawler

`sudo nohup python3 news.py &`

- 백그라운드에서 터미널이 종료되어도 계속 실행이 되는코드 (nohup, &)
