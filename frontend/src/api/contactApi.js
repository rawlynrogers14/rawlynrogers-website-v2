import axios from "axios";

const API_BASE_URL = "http://localhost:8080/api";

export async function getContact() {
  const response = await axios.get(`${API_BASE_URL}/contacts/1`);
  return response.data;
}

export async function getContactById(id) {
  const response = await axios.get(`${API_BASE_URL}/contacts/${id}`);
  return response.data;
}