import React from 'react';
import { useHistory } from 'react-router-dom';

const List = () => {
    const history = useHistory();

    const handleCreate = () => {
        history.push('/admin/products/create');
    }  //handleCreate vai redirecionar o usuário para a rota de criação
    
    return (
        <div className="admin-products-list">
            <button className="btn btn-primary btn-lg" onClick={handleCreate}>
                ADICIONAR
            </button>

        </div>
    )
}

export default List;