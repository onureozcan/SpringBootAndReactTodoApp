import cookie from "react-cookies";

const base = "http://localhost:8080";

class AuthDataSource {

    static loginSubscribers = [];

    static listenLoginChange(fnc) {
        AuthDataSource.loginSubscribers.push(fnc);
    }

    static register(userName, password, callback) {
        $.ajax({
            url: base + "/auth/register",
            type: "post",
            contentType: 'application/json',
            data: JSON.stringify({
                user: userName,
                pwd: password
            }),
            success: () => {
                callback({
                    success: true
                });
                this.logout();
            },
            error: (data) => {
                callback({
                    success: false,
                    data: data
                });
            }
        });

    }

    static login(userName, password, callback) {
        $.ajax({
            url: base + "/auth/login",
            type: "post",
            contentType: 'application/json',
            data: JSON.stringify({
                user: userName,
                pwd: password
            }),
            success: (data) => {
                let success = (data || "").length > 1;
                if (success) {
                    cookie.save('authorization', data);
                    setTimeout(() => {
                        // notify everyone that a login event occured!
                        this.loginSubscribers.forEach(x => x());
                    }, 400);
                }
                callback({
                    success: success,
                    data: data
                });
            },
            error: (data) => {
                callback({
                    success: false,
                    data: data
                });
            }
        });
    }

    static logout(){
        cookie.remove("authorization");
        setTimeout(() => {
            // notify everyone that a logout event occured!
            this.loginSubscribers.forEach(x => x());
        }, 400);
    }
}

export default AuthDataSource;