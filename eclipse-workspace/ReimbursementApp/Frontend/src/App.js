import { useState, useEffect } from 'react'
import Login from './components/Login'
import axios from 'axios'

const api = axios.create({ baseURL: 'http://localhost:3000' })

function App() {
    const [isLoggedIn, setIsLoggedIn] = useState(false)
    const [loggedInUser, setLoggedInUser] = useState('')
    const [requests, setRequests] = useState([])


    const loginSuccess = (credentials) => {
        setLoggedInUser(credentials)
        setIsLoggedIn(true)
    }

    return (
        <body>
            <div className="container">
                {!isLoggedIn && <Login onLoginSucess={loginSuccess}/>}
            </div>
        </body>
    );
}

export default App;