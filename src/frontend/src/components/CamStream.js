import React, { useState, useEffect } from "react";
import "../App.css";
import "bootstrap/dist/css/bootstrap.min.css";
import "react-confirm-alert/src/react-confirm-alert.css";
import "../util/StreamingConnection.js";
import { Button, Container } from "reactstrap";

class Streaming extends React.Component {
  render() {
    return (
      <>
        <Container>
          <br />
          <h3>Camera IP</h3>
          <img
            src="http://192.168.8.101:4747/video"
            alt="Loading..."/>
        </Container>
      </>
    );
  }
}
export default Streaming;
