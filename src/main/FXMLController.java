package main;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.sql.*;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.scene.control.cell.PropertyValueFactory;


public class FXMLController implements Initializable {

	@FXML
	private TableView<Biodata> biodataTableView;
	@FXML
	private TableColumn<?, ?> idTableColumn;
	@FXML
	private TableColumn<?, ?> namaTableColumn;
	@FXML
	private TableColumn<?, ?> alamatTableColumn;
	@FXML
	private TableColumn<?, ?> pekerjaanTableColumn;
	@FXML
	private TableColumn<?, ?> nomorHandphoneTableColumn;
	@FXML
	private TableColumn<?, ?> emailTableColumn;

	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		TableViewBiodata();
	}	
	private void TableViewBiodata() {
		ObservableList<Biodata> listBiodata = FXCollections.observableArrayList();

		String user = "myMSQLWINDOWS";
		String passwd = "msqlwindowsapp1";
		String url = "jdbc:mysql://localhost:3306/siadb";

		try ( Connection connection = DriverManager.getConnection(url, user, passwd)) {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM biodata");
			while (resultSet.next()) {
				String idTable = resultSet.getString("id");
				String namaTable = resultSet.getString("nama");
				String alamatTable = resultSet.getString("alamat");
				String pekerjaanTable = resultSet.getString("pekerjaan");
				String nomorHandphoneTable = resultSet.getString("nomorhandphone");
				String emailTable = resultSet.getString("email");

				Biodata biodata = new Biodata();
				biodata.setId(idTable);
				biodata.setNama(namaTable);
				biodata.setAlamat(alamatTable);
				biodata.setPekerjaan(pekerjaanTable);
				biodata.setPhone(nomorHandphoneTable);
				biodata.setEmail(emailTable);

				listBiodata.add(biodata);
			}
		} catch (SQLException exception) {
			exception.printStackTrace();
		}

		idTableColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
		namaTableColumn.setCellValueFactory(new PropertyValueFactory<>("nama"));
		alamatTableColumn.setCellValueFactory(new PropertyValueFactory<>("Alamat"));
		pekerjaanTableColumn.setCellValueFactory(new PropertyValueFactory<>("Pekerjaan"));
		nomorHandphoneTableColumn.setCellValueFactory(new PropertyValueFactory<>("Phone"));
		emailTableColumn.setCellValueFactory(new PropertyValueFactory<>("Email"));

		biodataTableView.setItems(listBiodata);

	}
}
