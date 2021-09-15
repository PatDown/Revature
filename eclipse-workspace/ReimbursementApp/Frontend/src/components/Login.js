import React from 'react'

function Login({ onLoginSuccess }) {
    const [username, setUsername] = useState('')
    const [password, setPassword] = useState('')

    const onSuccess = (res) => {
        console.log('Login Success: currentAccount:', res.profileObj)
        onLoginSuccess(res.profileObj.name)
    }

    const onFailure = (res) => {
        console.log('Login failed: res:', res)
    }

    return (
        <div>
            <form action='index.html' method='login' onSubmit={onSubmit}>
                <textarea className='username-entry' placeholder='Username'
                    onChange={(e) => setUsername(e.target.value)} />
                <textarea className='password-entry' placeholder='Password'
                    onChange={(e) => setPassword(e.target.value)} />

                <div className='button-container'>
                    <button type='submit' className='login-button'>Login</button>
                    <button type='reset' className='cancel-button' onClick={oncancel}>Cancel</button>
                </div>
            </form>

        </div>
    )
}