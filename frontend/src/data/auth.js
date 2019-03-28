const base = "http://localhost:8080";

class AuthDataSource {
    static register(userName, password, callback) {
        $.ajax({
            url: base + "/auth/register",
            type:"post",
            contentType: 'application/json',
            data: JSON.stringify({
                user: userName,
                pwd: password
            }),
            success: () => {
                callback({
                    success: true
                });
            },
            error: (data) => {
                callback({
                    success: false,
                    data:data
                });
            }
        });

    }
}

export default AuthDataSource;