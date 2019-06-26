FROM mcr.microsoft.com/windows/servercore:1809

ENV CONFIG_SERVICE_NAME="Benlai.Config.Service"

LABEL author="sheep.yang" \
	app.name="benlai.config.service" \
	app.service.name=${CONFIG_SERVICE_NAME} \
	app.http.port="10083"

WORKDIR c:/users/app

COPY app c:/users/app

CMD ["Benlai.ConfigService.exe", "-d"]

