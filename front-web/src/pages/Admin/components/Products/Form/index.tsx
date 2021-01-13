import { makeRequest } from 'core/utils/request';
import React, { useState } from 'react';
import BaseForm from '../../BaseForm';
import './styles.scss';

//vai representar a estrutura de dados que há no formulário
type FormState = {
    name: string;
    price: string;
    category: string;
    description: string;
}

type FormEvent = React.ChangeEvent<HTMLInputElement | HTMLSelectElement | HTMLTextAreaElement>;

const Form = () => {

    const [formData, setFormData] = useState<FormState>({
        name: '',
        price: '',
        category: '1',
        description: ''
    }); //um estado que terá todos os valores do formulário - nome, categoria, preço...

    const handleOnChange = (event: FormEvent) => {
        const name = event.target.name; //lendo qual campo está sendo editado
        const value = event.target.value;

        setFormData(data => ({ ...data, [name]: value }));     
    }

    const handleSubmit = (event: React.FormEvent<HTMLFormElement>) => {
        event.preventDefault();
        const payload = {
            ...formData,
            imgUrl: 'https://compass-ssl.xbox.com/assets/c8/9d/c89d04a8-ba6d-496d-aa7e-5b1cf0d60ea9.jpg?n=10202018_Panes-3-Up-0_Hero-SX_347x347.jpg',
            categories: [{ id: formData.category }]

        }

        makeRequest({ url: '/products', method: 'POST', data: payload })
            .then(() => {
            setFormData({ name: '', category: '', price: '', description: ''});
        });
    }


    return (
        <form onSubmit={handleSubmit}>
            <BaseForm
                title="cadastrar um produto">
                <div className="row">
                    <div className="col-6">
                        <input
                            value={formData.name}
                            name="name" //nome que identifica o campo
                            type="text"
                            className="form-control mb-5"
                            onChange={handleOnChange}
                            placeholder="Nome do produto"
                        />
                        <select
                            value={formData.category}
                            className="form-control mb-5"
                            onChange={handleOnChange}
                            name="category"
                        >
                            <option value="1">Livros</option>
                            <option value="2">Eletrônicos</option>
                            <option value="3">Computadores</option>
                        </select>
                        <input
                            value={formData.price}
                            name="price"
                            type="text"
                            className="form-control"
                            onChange={handleOnChange}
                            placeholder="Preço"
                        />
                    </div>
                    <div className="col-6">
                        <textarea
                            name="description"
                            value={formData.description}
                            onChange={handleOnChange}
                            className="form-control"
                            cols={30}
                            rows={10}
                        />
                    </div>
                </div>
            </BaseForm>
        </form>
    );
};

export default Form;