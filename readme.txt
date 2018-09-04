first step: build the image based on dockerfile (file in springGreetingsRepository root)

docker build -t spring_greetings_client:v1 -f dockerfile .

second step: run image with 1 param - message to server

docker run -it --network=host spring_greetings_client:v1 "Hello, server"

If message contains word "specified", server sends response with srting "(specified response)"