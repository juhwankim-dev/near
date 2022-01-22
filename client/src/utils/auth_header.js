export function authHeader() {
    const user = JSON.parse(localStorage.getItem('user'));
  
    if (user && user.authToken) {
      return { Authorization: `Bearer ${user.authToken}` };
    } else {
      return {};
    }
  }