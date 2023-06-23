import React, { useEffect, useState } from 'react';
import { useLocation , Link } from 'react-router-dom';

const CreateOrder = () =>{
  const location = useLocation();
  const params = new URLSearchParams(location.search);
  const personId = params.get('personId');
  const firstName = params.get('personfirst');
  const lastName = params.get('personlast');
  console.log(firstName)

  return(
  <div>
   <h1> {personId} </h1>
   <h1> {firstName} </h1>
   <h1> {lastName} </h1>
  </div>
  )
}

export default CreateOrder;