const hbs = require('handlebars');
const layout = require('./layout');

hbs.registerPartial('profile-form',
    `<h1>{{title}}</h1>
     <form action="{{#if url}}{{url}}{{else}}/profiles{{/if}}" method="post" enctype="multipart/form-data">
         <p><label>Nickname:<br/><input 
            type="text" 
            pattern="[a-zA-Z0-9_]{1,255}" 
            title="Only word characters are allowed!" 
            name="nickName" 
            placeholder="Nickname" 
            {{#if view}}
                value="{{data.nickName}}"
            {{/if}}
            {{#if create}}
                required
            {{else}}
                readonly
            {{/if}}            
         ></label></p>     
            
         <p>Forename:<br /><input 
            type="text" 
            pattern="[a-zA-Z0-9_]{1,255}" 
            title="Only word characters are allowed!" 
            name="firstName" 
            placeholder="Forename" 
            {{#if view}}
                value="{{data.firstName}}"
            {{/if}}
            {{#if edit}}
                required
            {{else}}
                readonly
            {{/if}}
         ></p>
         
         <p>Surname:<br /><input 
            type="text" 
            pattern="[a-zA-Z0-9_]{1,255}" 
            title="Only word characters are allowed!" 
            name="lastName" 
            placeholder="Surname" 
            {{#if view}}
                value="{{data.lastName}}"
            {{/if}}
            {{#if edit}}
                required
            {{else}}
                readonly
            {{/if}}
         ></p>
         
         <p>Description:<br /><textarea 
            name="description" 
            rows="4" 
            cols="30" 
            placeholder="Description"
            {{#unless edit}}
                readonly
            {{/unless}}
         >{{#if view}}{{data.description}}{{/if}}</textarea></p>
         
         <div class="socialLinks">
            <h4>Social media links</h4>
            {{#if edit}}
                <p>Facebook:<br/>
                    <input
                        style="background-color: #3b5998; color: white;" 
                        type="text" pattern="https?://.+" 
                        title="URL must start with https?://" 
                        name="fbProfile" 
                        placeholder="Facebook"  
                        {{#if view}}
                            value="{{data.facebookProfile}}"
                        {{/if}}
                        {{#unless edit}}
                            readonly
                        {{/unless}}
                    />
                </p>
            {{else if data.facebookProfile}}
                <p><a target="_blank" href="{{data.facebookProfile}}">Facebook</a></p>
            {{else}}
            {{/if}}
            
            {{#if edit}}
                <p>Twitter:<br/>
                    <input
                        style="background-color: #4099FF; color: white;" 
                        type="text" pattern="https?://.+" 
                        title="URL must start with https?://" 
                        name="twProfile" 
                        placeholder="Twitter" 
                        {{#if view}}
                            value="{{data.twitterProfile}}"
                        {{/if}}
                        {{#unless edit}}
                            readonly
                        {{/unless}}
                    />
                </p>
            {{else if data.twitterProfile}}
                <p><a target="_blank" href="{{data.twitterProfile}}">Twitter</a></p>
            {{else}}
            {{/if}}
            
            {{#if edit}}
                <p>Xing:<br/>
                    <input
                        style="background-color: #8a3ab9; color: white;" 
                        type="text" pattern="https?://.+" 
                        title="URL must start with https?://" 
                        name="xingProfile" 
                        placeholder="Xing"   
                        {{#if view}}
                            value="{{data.xingProfile}}"
                        {{/if}}
                        {{#unless edit}}
                            readonly
                        {{/unless}}
                    />
                </p>
            {{else if data.xingProfile}}
                <p><a target="_blank" href="{{data.xingProfile}}">Xing</a></p>
            {{else}}
            {{/if}}
            
            {{#if view}}
                <p><img src="{{#unless edit}}{{data.nickName}}/{{/unless}}{{data.profilePicture}}" alt="No profile picture available." style="width:200px"></p>
            {{/if}}            
            {{#if edit}}
                <p><input type="file" accept="image/jpeg, image/png" name="imagefile"></p>
            {{/if}}
            
            
        </div>
        {{#if edit}}
            <p><button type="submit">Erstellen</button></p>    
        {{else}}
        {{/if}}
     </form>
     `);

hbs.registerPartial('profile-exists', `
    <h1>Profile Already Exists!</h1>
    <a href="./new">Create New User</a>
    <a href="./profiles/{{nickName}}">View existing User</a>
    `);

/**
 *
 */
function renderNewProfileView () {
    const viewModel = { bodyPartial: 'profile-form',
        title: "User Profile Creation",
        create: true,
        edit: true,
        view: false
    };
    return layout(viewModel);
}

/**
 *
 * @param data
 */
function renderExistingProfileView(data, title) {
    const viewModel = { bodyPartial: 'profile-form',
        title: title,
        create: false,
        edit: false,
        view: true,
        data: data
    };
    return layout(viewModel);
}

function renderProfileEditView(data) {
    const viewModel = { bodyPartial: 'profile-form',
        title: "User Profile Editorial",
        url: "./edit",
        create: false,
        edit: true,
        view: true,
        data: data
    };
    return layout(viewModel);
}

function renderAlreadyExistingProfileView(nickName) {
    const viewModel = { bodyPartial: 'profile-exists',
        nickName: nickName
    }
    return layout(viewModel);
}


// This module is a collection of views related to a single profile
// Note: for now we only have one view, but in the end there will be
// a couple of views being exported.
module.exports = {
    newProfileView: renderNewProfileView,
    existingProfileView: renderExistingProfileView,
    editProfileView: renderProfileEditView,
    existingProfileCreate: renderAlreadyExistingProfileView
};