import axios from "axios";

const API_BASE_URL = "/api";

export async function getMediaById(id) {
  const response = await axios.get(`${API_BASE_URL}/media/${id}`);
  return response.data;
}