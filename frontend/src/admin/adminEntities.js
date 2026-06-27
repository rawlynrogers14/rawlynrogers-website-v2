export const adminEntities = {
  projects: {
    label: "Projects",
    listUrl: "/api/projects",
    adminUrl: "/api/admin/projects",
    displayField: "title",
    fields: [
      { name: "title", label: "Title", type: "text", required: true },
      { name: "description", label: "Description", type: "textarea" },
      { name: "technologies", label: "Technologies", type: "textarea" },
      { name: "githubLink", label: "GitHub Link", type: "url" },
      { name: "projectDate", label: "Project Date", type: "text" },
    ],
  },

  contacts: {
    label: "Contact",
    listUrl: "/api/contacts",
    adminUrl: "/api/admin/contacts",
    displayField: "email",
    fields: [
      { name: "firstName", label: "First Name", type: "text", required: true },
      { name: "middleName", label: "Middle Name", type: "text" },
      { name: "lastName", label: "Last Name", type: "text", required: true },
      { name: "email", label: "Email", type: "email" },
      { name: "githubLink", label: "GitHub Link", type: "url" },
      { name: "linkedinLink", label: "LinkedIn Link", type: "url" },
      { name: "profileImageUrl", label: "Profile Image URL", type: "url" },
      { name: "resumePdfUrl", label: "Resume PDF URL", type: "url" },
      { name: "cvPdfUrl", label: "CV PDF URL", type: "url" },
      { name: "aboutMe", label: "About Me", type: "textarea" },
    ],
  },

  media: {
    label: "Media",
    listUrl: "/api/media",
    adminUrl: "/api/admin/media",
    uploadUrl: "/api/admin/media/upload",
    displayField: "fileName",
    fields: [
      { name: "fileName", label: "File Name", type: "text", required: true },

      {name: "fileType", label: "File Type", type: "select", required: true, options:
        [
          { label: "Image", value: "image/jpeg" },
          { label: "PDF", value: "application/pdf" },
        ],
      },

      { name: "upload", label: "Upload File", type: "file" },
      { name: "description", label: "Description", type: "textarea" },
      { name: "uploadedAt", label: "Uploaded At", type: "datetime-local", readOnly: true },
    ],
  },
};