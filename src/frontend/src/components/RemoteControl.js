import React from "react";
import "bootstrap/dist/css/bootstrap.min.css";
import "react-confirm-alert/src/react-confirm-alert.css";

import { Button, Container } from "reactstrap";

class RemoteControl extends React.Component {
  constructor(props) {
    super(props);

    this.state = {
      btnNameRelay0: "Abrir entrada",
      btnNameRelay1: "Abrir salida",
    };
  }

  activateRelay(id) {
    if (id == 0) {
      this.setState({ btnNameRelay0: "Activando..." });
      setTimeout(
        function () {
          this.setState({ btnNameRelay0: "Abrir entrada" });
        }.bind(this),
        2050
      );
    }
    if (id == 1) {
      this.setState({ btnNameRelay1: "Activando..." });
      setTimeout(
        function () {
          this.setState({ btnNameRelay1: "Abrir salida" });
        }.bind(this),
        2050
      );
    }

    fetch("http://192.168.8.100:8080/api/v1/activateRelay?id=" + id)
      .then((res) => res.json())
      .then((json) => {
        this.setState({});
      });
  }

  render() {
    console.log(this.props.parentToChild);
    const { btnNameRelay0 } = this.state;
    const { btnNameRelay1 } = this.state;

    return (
      <>
        <Container>
          <br />
          <h3>Control remoto</h3>
          <Button color="primary" onClick={() => this.activateRelay(0)}>
            &nbsp;&nbsp;{btnNameRelay0}&nbsp;&nbsp;
          </Button>
          &nbsp;&nbsp;&nbsp;
          <Button color="primary" onClick={() => this.activateRelay(1)}>
            &nbsp;&nbsp;&nbsp;{btnNameRelay1}&nbsp;&nbsp;&nbsp;&nbsp;
          </Button>
        </Container>
      </>
    );
  }
}

export default RemoteControl;
