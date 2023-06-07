import React from 'react';
import { Navigate } from 'react-router-dom';
import jwt from "jwt-decode"
import { useAuthHeader,useIsAuthenticated,useSignOut } from 'react-auth-kit';
const RequireRole = ({ allowedRoles, children }) => {
  // Pobierz informacje o zalogowanym użytkowniku z kontekstu lub innego źródła
  const isAuth = useIsAuthenticated();
  const singOut = useSignOut();
  if(isAuth())
  {
    const token = useAuthHeader();
    const userData = jwt(token())
    

  if (!allowedRoles.includes(userData.roles[0])) {
    // Jeśli użytkownik nie jest zalogowany lub nie ma wymaganego uprawnienia, przekieruj go na inną stronę
    singOut();
    return <Navigate to="/login" />;
  }

  // Jeśli użytkownik ma wymagane uprawnienia, renderuj komponent
  return children;
  }
  else
    return <Navigate to="/login" />;
    
};

export default RequireRole;
