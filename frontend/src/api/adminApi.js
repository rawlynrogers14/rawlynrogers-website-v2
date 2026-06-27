const API_BASE = "http://localhost:8080";

export function getToken() {
  return localStorage.getItem("adminToken");
}

export function logout() {
  localStorage.removeItem("adminToken");
}

async function request(path, options = {}) {
  const token = getToken();

  const response = await fetch(`${API_BASE}${path}`, {
    ...options,
    headers: {
      "Content-Type": "application/json",
      ...(token ? { Authorization: `Bearer ${token}` } : {}),
      ...options.headers,
    },
  });

  if (!response.ok) {
    throw new Error(`Request failed with status ${response.status}`);
  }

  const text = await response.text();

  if (!text) {
    return null;
  }

  return JSON.parse(text);
}

export async function login(username, password) {
  const data = await request("/api/auth/login", {
    method: "POST",
    body: JSON.stringify({ username, password }),
  });

  localStorage.setItem("adminToken", data.token);
  return data;
}

export async function uploadMedia(entity, file, description) {
  const token = getToken();

  const formData = new FormData();
  formData.append("file", file);

  if (description) {
    formData.append("description", description);
  }

  const response = await fetch(`${API_BASE}${entity.uploadUrl}`, {
    method: "POST",
    headers: {
      Authorization: `Bearer ${token}`,
    },
    body: formData,
  });

  if (!response.ok) {
    throw new Error(`Upload failed with status ${response.status}`);
  }

  return response.json();
}

export const adminApi = {
  getAll: (entity) => request(entity.listUrl),

  create: (entity, record) =>
    request(entity.adminUrl, {
      method: "POST",
      body: JSON.stringify(record),
    }),

  update: (entity, id, record) =>
    request(`${entity.adminUrl}/${id}`, {
      method: "PUT",
      body: JSON.stringify(record),
    }),

  delete: (entity, id) =>
    request(`${entity.adminUrl}/${id}`, {
      method: "DELETE",
    }),
};