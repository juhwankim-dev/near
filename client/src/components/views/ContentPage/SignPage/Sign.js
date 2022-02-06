import SignDetail from "./SignDetail"
import { useNavigate } from "react-router-dom";

function Sign({key, id, title}){
    let navigate = useNavigate();
return(
    <div>
        <button className="effect" type="button" onClick={ ()=>{navigate(`${id}`)} }
      > {title}</button>
    </div>
)
}

export default Sign;