import axios from "axios";

const API_BASE_URL = "http://localhost:8080/api";

export async function getProjects() {
  const response = await axios.get(`${API_BASE_URL}/projects`);
  return response.data;
}

export async function getProjectById(id) {
  const response = await axios.get(`${API_BASE_URL}/projects/${id}`);
  return response.data;
}