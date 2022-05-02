import React from "react";
import "./App.css";
import { useState } from "react";
import "bootstrap/dist/css/bootstrap.min.css";
import getRequest from "./util/ApiRequest";
import AccessControlList from "./components/AccessControlList";
import CamStream from "./components/CamStream";
import RemoteControl from "./components/RemoteControl";

export default function App() {
  const [data, setData] = useState("");

  return (
    <>
      <RemoteControl />
      <CamStream />
      <AccessControlList />
    </>
  );
}
