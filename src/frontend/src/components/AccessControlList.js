import React, { useState, useEffect } from "react";
import "../App.css";
import "bootstrap/dist/css/bootstrap.min.css";
import { confirmAlert } from "react-confirm-alert"; // Import
import "react-confirm-alert/src/react-confirm-alert.css"; // Import css
import TimeField from "react-simple-timefield";
import Alert from "@mui/material/Alert";

import {
  Table,
  Button,
  Container,
  Modal,
  ModalHeader,
  ModalBody,
  FormGroup,
  ModalFooter,
} from "reactstrap";

function formatTS(timeStmp) {
  if (timeStmp == "" || timeStmp == null) {
    return "Indefinido";
  }
  const dateObject = new Date(timeStmp);
  const formated = dateObject.toLocaleTimeString([], {
    day: "2-digit",
    month: "2-digit",
    year: "numeric",
    hour: "2-digit",
    minute: "2-digit",
  });
  return formated;
}

function deletePost(id) {
  fetch("http://192.168.8.100:8080/api/v1/authcars?id=" + id, {
    method: "DELETE",
  })
    .then(async (response) => {
      const data = await response.json();

      if (!response.ok) {
        const error = (data && data.message) || response.status;
        return Promise.reject(error);
      }
    })
    .catch((error) => {
      console.error("There was an error!", error);
    });
}

function sort_by_key(array, key) {
  return array.sort(function (a, b) {
    var x = a[key];
    var y = b[key];
    return x < y ? -1 : x > y ? 1 : 0;
  });
}
class AccessControlList extends React.Component {
  OPEN_TIME = "09:00";
  CLOSE_TIME = "21:00";
  constructor(props) {
    super(props);

    this.state = {
      parentToChild: "asdsd",
      time: "12:34",

      Iopen_time: this.OPEN_TIME,
      Iclose_time: this.CLOSE_TIME,
      items: [],

      DataisLoaded: false,
      modalActualizar: false,
      modalInsertar: false,
      form: {
        plate: "",
        register_date: "",
        drop_out_date: "",
        open_time: "",
        close_time: "",
      },
    };
  }

  refreshPage() {
    window.location.reload(true);
  }

  delayedRefreshPage() {

    setTimeout(
      function () {
        window.location.reload(true);
      }.bind(this),
      100
    );
  }

  componentDidMount() {
    fetch("http://192.168.8.100:8080/api/v1/authcars")
      .then((res) => res.json())
      .then((json) => {
        //json = json.sort((a, b) => (a.attr > b.attr) - (a.attr < b.attr))
        this.setState({
          data: sort_by_key(json, "plate"),
          DataisLoaded: true,
        });
      });
  }

  getOpenTimeField(newTime) {
    this.setState({ Iopen_time: newTime });
  }

  getCloseTimeField(newTime) {
    this.setState({ Iclose_time: newTime });
  }

  render() {
    console.log(this.props.parentToChild);
    const { Iopen_time } = this.state;
    const { Iclose_time } = this.state;

    const { DataisLoaded, items } = this.state;
    if (!DataisLoaded)
      return (
        <div>
          <h1> Pleses wait some time.... </h1>{" "}
        </div>
      );

    return (
      <>
        <Container>
          <h2>
            <br />
            Lista de acceso&nbsp;&nbsp;
            <Button color="success" onClick={() => this.mostrarModalInsertar()}>
              Añadir matrícula
            </Button>{" "}
          </h2>

          <br />
          <Table>
            <thead>
              <tr>
                <th>Acción</th>
                <th>Matrícula</th>
                <th>Fecha de alta</th>
                <th>Fecha de baja</th>
                <th>Entrada/salida desde</th>
                <th>Entrada/salida hasta</th>
              </tr>
            </thead>

            <tbody>
              {this.state.data.map((dato) => (
                <tr key={dato.plate}>
                  <td>
                    <Button
                      color="primary"
                      onClick={() => this.mostrarModalActualizar(dato)}
                    >
                      Modificar
                    </Button>{" "}
                    <Button color="danger" onClick={() => this.eliminar(dato)}>
                      Eliminar
                    </Button>
                  </td>
                  <td style={{ fontWeight: "bold" }}>{dato.plate}</td>
                  <td>{formatTS(dato.register_date)}</td>
                  <td>{formatTS(dato.drop_out_date)}</td>
                  <td
                    style={{ textAlignVertical: "center", textAlign: "center" }}
                  >
                    {dato.open_time}
                  </td>
                  <td
                    style={{ textAlignVertical: "center", textAlign: "center" }}
                  >
                    {dato.close_time}
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        </Container>

        <Modal isOpen={this.state.modalActualizar}>
          <ModalHeader>
            <div>
              <h3>Editar Registro</h3>
            </div>
          </ModalHeader>

          <ModalBody>
            <FormGroup>
              <label>Matrícula:</label>

              <input
                className="form-control"
                readOnly
                type="text"
                value={this.state.form.plate}
              />
            </FormGroup>

            <FormGroup>
              <label>Fecha de alta:</label>
              <input
                className="form-control"
                name="register_date"
                type="date"
                onChange={this.handleChange}
              />
            </FormGroup>

            <FormGroup>
              <label>Fecha de baja:</label>
              <input
                className="form-control"
                name="drop_out_date"
                type="date"
                onChange={this.handleChange}
              />
            </FormGroup>

            <FormGroup>
              <label>Entrada/salida desde:</label>
              <TimeField
                value={Iopen_time}
                type="text"
                name="open_time"
                className="form-control"
                onChange={(event, value) => this.getOpenTimeField(value)}
                input={<input type="text" name="open_time" />}
                colon=":"
              />
            </FormGroup>

            <FormGroup>
              <label>Entrada/salida hasta:</label>
              <TimeField
                value={Iclose_time}
                type="text"
                name="close_time"
                className="form-control"
                onChange={(event, value) => this.getCloseTimeField(value)}
                input={<input type="text" name="close_time" />}
                colon=":"
              />
            </FormGroup>
          </ModalBody>

          <ModalFooter>
            <Button
              color="primary"
              onClick={() => this.editar(this.state.form)}
            >
              Editar
            </Button>
            <Button color="danger" onClick={() => this.cerrarModalActualizar()}>
              Cancelar
            </Button>
          </ModalFooter>
        </Modal>

        <Modal isOpen={this.state.modalInsertar}>
          <ModalHeader>
            <div>
              <h3>Insertar nueva matrícula</h3>
            </div>
          </ModalHeader>

          <ModalBody>
            <FormGroup>
              <label>Matrícula:</label>
              <input
                required="True"
                className="form-control"
                name="plate"
                type="text"
                maxLength="7"
                onChange={this.handleChange}
                onInput={(e) => (
                  (e.target.value = ("" + e.target.value).toUpperCase()),
                  (e.target.value = ("" + e.target.value).replace(" ", ""))
                )}
              />
            </FormGroup>

            <FormGroup>
              <label>Fecha de alta:</label>
              <input
                className="form-control"
                name="register_date"
                type="date"
                onChange={this.handleChange}
              />
            </FormGroup>

            <FormGroup>
              <label>Fecha de baja:</label>
              <input
                className="form-control"
                name="drop_out_date"
                type="date"
                onChange={this.handleChange}
              />
            </FormGroup>

            <FormGroup>
              <label>Entrada/salida desde:</label>
              <TimeField
                value={Iopen_time}
                type="text"
                name="open_time"
                className="form-control"
                onChange={(event, value) => this.getOpenTimeField(value)}
                input={<input type="text" name="open_time" />}
                colon=":"
              />
            </FormGroup>

            <FormGroup>
              <label>Entrada/salida hasta:</label>
              <TimeField
                value={Iclose_time}
                type="text"
                name="close_time"
                className="form-control"
                onChange={(event, value) => this.getCloseTimeField(value)}
                input={<input type="text" name="close_time" />}
                colon=":"
              />
            </FormGroup>
          </ModalBody>

          <ModalFooter>
            <Button color="primary" onClick={() => this.insertar()}>
              Insertar
            </Button>
            <Button
              className="btn btn-danger"
              onClick={() => this.cerrarModalInsertar()}
            >
              Cancelar
            </Button>
          </ModalFooter>
        </Modal>
      </>
    );
  }

  mostrarModalActualizar = (dato) => {
    this.setState({ Iopen_time: dato.open_time });
    this.setState({ Iclose_time: dato.close_time });

    this.setState({
      form: dato,
      modalActualizar: true,
    });
  };

  cerrarModalActualizar = () => {
    this.setState({ modalActualizar: false });
  };

  mostrarModalInsertar = () => {
    this.setState({
      modalInsertar: true,
    });
  };

  cerrarModalInsertar = () => {
    this.setState({ modalInsertar: false });
  };

  editar = (dato) => {
    const newPlate = { ...this.state.form };
    if (newPlate.register_date == "") {
      newPlate.register_date = null;
    }
    if (newPlate.drop_out_date == "") {
      newPlate.drop_out_date = null;
    }
    newPlate.open_time = this.state.Iopen_time;
    newPlate.close_time = this.state.Iclose_time;

    //this.delayedRefreshPage();
    console.log(newPlate);

    const requestOptions = {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(newPlate),
    };

    fetch("http://192.168.8.100:8080/api/v1/authcars", requestOptions)
      .then((response) => response.json())
      .then((data) => this.setState({ postId: data.id }));

    this.setState({ modalInsertar: false });

    this.delayedRefreshPage();
  };

  eliminar = (dato) => {
    var contador = 0;
    var arreglo = this.state.data;
    confirmAlert({
      title: "Confirmación",
      message: "Quiere eliminar la matrícula " + dato.plate + "?",
      buttons: [
        {
          label: "Sí",
          onClick: () => (
            (contador = 0),
            (arreglo = this.state.data),
            arreglo.map((registro) => {
              if (dato.plate == registro.plate) {
                deletePost(this.state.data[contador].id);
                //arreglo.splice(contador, 1);
              }
              contador++;
            }),
            this.componentDidMount(),
            this.delayedRefreshPage()
          ),
        },
        {
          label: "No",
          // onClick: () => (acepta_eliminar = false),
        },
      ],
      closeOnClickOutside: false,
    });
  };

  insertar = () => {
    var valorNuevo = { ...this.state.form };

    //var lista = this.state.data;
    // console.log({ ...this.state.form });
    //lista.push(valorNuevo);

    const newPlate = { ...this.state.form };
    if (newPlate.register_date == "") {
      newPlate.register_date = null;
    }
    if (newPlate.drop_out_date == "") {
      newPlate.drop_out_date = null;
    }
    newPlate.open_time = this.state.Iopen_time;
    newPlate.close_time = this.state.Iclose_time;

    if (newPlate.plate == "" || newPlate.plate.length < 7) {
      this.setState({ modalInsertar: false });

      confirmAlert({
        title: "Alerta",
        message: "La matrícula debe tener 7 caracteres",
        buttons: [
          {
            label: "Aceptar",
            onClick: () => this.setState({ modalInsertar: true }),
          },
        ],
        closeOnClickOutside: false,
      });
    } else {
      const requestOptions = {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(newPlate),
      };

      fetch("http://192.168.8.100:8080/api/v1/authcars", requestOptions)
        .then((response) => response.json())
        .then((data) => this.setState({ postId: data.id }));

      this.setState({ modalInsertar: false });
      this.delayedRefreshPage();
      console.log(newPlate);
    }
    //this.setState({ Iopen_time: this.OPEN_TIME });
    //this.setState({ Iclose_time: this.CLOSE_TIME });

    //this.setState({ modalInsertar: false, data: lista });
  };

  handleChange = (e) => {
    this.setState({
      form: {
        ...this.state.form,
        [e.target.name]: e.target.value,
      },
    });
  };
}

export default AccessControlList;
