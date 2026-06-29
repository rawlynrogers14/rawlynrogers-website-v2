import axios from "axios";

const API_BASE_URL = "http://localhost:8080/api";

export async function getActiveProfile() {
  const response = await axios.get(`${API_BASE_URL}/profiles/active`);
  return response.data;
}