export const adminEntities = {
  profiles: {
    label: "Profiles",
    listUrl: "/api/profiles",
    adminUrl: "/api/admin/profiles",
    displayField: "name",
    fields: [
      { name: "name", label: "Profile Name", type: "text", required: true },

      {
        name: "contact",
        label: "Contact",
        type: "entity-select",
        source: "contacts",
        displayField: "email",
      },

      {
        name: "projects",
        label: "Projects",
        type: "entity-multiselect",
        source: "projects",
        displayField: "title",
        payloadName: "projectIds",
      },
    ],
  },

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

      {
        name: "contributors",
        label: "Contributors",
        type: "entity-multiselect",
        source: "contacts",
        displayField: "email",
        payloadName: "contributorIds",
      },

      {
        name: "slideshow",
        label: "Project Slideshow",
        type: "entity-multiselect",
        source: "media",
        displayField: "fileName",
        payloadName: "slideshowIds",
      },
    ],
  },

  contacts: {
    label: "Contacts",
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

      {
        name: "profileImage",
        label: "Profile Image",
        type: "entity-select",
        source: "media",
        displayField: "fileName",
      },

      {
        name: "resumePdf",
        label: "Resume PDF",
        type: "entity-select",
        source: "media",
        displayField: "fileName",
      },

      {
        name: "cvPdf",
        label: "CV PDF",
        type: "entity-select",
        source: "media",
        displayField: "fileName",
      },

      {
        name: "slideshow",
        label: "Contact Slideshow",
        type: "entity-multiselect",
        source: "media",
        displayField: "fileName",
      },

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
      { name: "description", label: "Description", type: "textarea" },

      {
        name: "fileType",
        label: "File Type",
        type: "display",
      },

      {
        name: "uploadedAt",
        label: "Uploaded At",
        type: "display",
      },

      { name: "upload", label: "Upload File", type: "file", createOnly: true },
    ],
  },
};