from flask import Flask

app = Flask(__name__)


@app.route("/")
def hello():
    return "Hello, Docker Flask!"


if __name__ == "__main__":
    # host='0.0.0.0' allows access from outside the container
    app.run(host="0.0.0.0", port=5000)
